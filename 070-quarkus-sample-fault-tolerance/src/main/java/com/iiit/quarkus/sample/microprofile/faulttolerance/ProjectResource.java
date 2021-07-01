/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iiit.quarkus.sample.microprofile.faulttolerance;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.jboss.logging.Logger;

@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);

	private AtomicLong counter = new AtomicLong(0);	
	
	private AtomicLong circuitBreakerCounter = new AtomicLong(0);	

	private Float failRatio = 0.5f;

	// 注入ProjectService对象
	@Inject
	ProjectService service;

	public ProjectResource() {
	}

	// ********Quarkus重试的实现*******
	// 这种方法在50%的时间内失败。但是，在失败的情况下，由于使用了{@Retry}注释，该方法会自动再次被重新调用（最多4次）。
	// 因为用户很少出现4次故障，这意味着发生故障的概率很低。
	@GET
	@Retry(maxRetries = 4, retryOn = RuntimeException.class)
	public List<Project> list() {
		final Long invocationNumber = counter.getAndIncrement();
		maybeFail(String.format("ProjectResourcee的list方法 invocation #%d failed",invocationNumber));
		LOGGER.infof("ProjectResourcee的list方法  invocation #%d returning successfully",invocationNumber);
		return service.getAllProject();
	}

	@GET
	@Path("/{id}")
	@Retry(maxRetries = 4, retryOn = RuntimeException.class)
	public Response get(@PathParam("id") int id) {
		final Long invocationNumber = counter.getAndIncrement();
		maybeFail(String.format("CoffeeResource#coffees() invocation #%d failed",invocationNumber));
		LOGGER.infof("ProjectResource的get() invocation #%d returning successfully",invocationNumber);

		Project project = service.getProjectById(id);
		// if project with given id not found, return 404
		if (project == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(project).build();
	}

	// 引入一些人为的故障
	private void maybeFail(String failureLogMessage) {
		if (new Random().nextFloat() < failRatio) {
			LOGGER.error(failureLogMessage);
			throw new RuntimeException("Resource failure.");
		}
	}
	

	// ********Quarkus超时和回退的实现*******
	@GET
	@Path("/recommendations/{id}")
	@Timeout(250)
	@Fallback(fallbackMethod = "fallbackRecommendations")
	public List<Project> recommendations(@PathParam("id") int id) {
		long started = System.currentTimeMillis();
		final long invocationNumber = counter.getAndIncrement();

		try {
			randomDelay();
			LOGGER.infof("ProjectResource的recommendations() 调用 #%d returning successfully",
					invocationNumber);
			return Collections.singletonList(service.getProjectById(id));
		} catch (InterruptedException e) {
			LOGGER.errorf("ProjectResource的recommendations() 调用 #%d timed out after %d ms",
					invocationNumber, System.currentTimeMillis() - started);
			return null;
		}
	}
	
	// 引入人为的延误
    private void randomDelay() throws InterruptedException {
    	long random = new Random().nextInt(500);
    	LOGGER.info("随机数random:"+random);
    	Thread.sleep(random);			
	}
	
    // 推荐的后备方法
 	public List<Project> fallbackRecommendations(int id) {
 		LOGGER.info("Falling back to ProjectResource 的 fallbackRecommendations()"); 		
 		return service.getRecommendations(id); 		
 	}

		

	// ********Quarkus熔断器的实现*******
	@GET
	@Path("/availability/{id}")
	public Response availability(@PathParam("id") int id) {
		final Long invocationNumber = counter.getAndIncrement();		

		try {			
			String   availability = getAvailability(id);
			LOGGER.infof("ProjectResource的availability() 调用 #%d returning successfully",	invocationNumber);
			return Response.ok(availability).build();
		} catch (RuntimeException e) {
			String message = e.getClass().getSimpleName() + ": "+ e.getMessage();
			LOGGER.errorf("ProjectResource 的availability方法 调用 #%d failed: %s",	invocationNumber, message);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
		}
	}
	
	@CircuitBreaker(requestVolumeThreshold = 4)
	private String getAvailability(int id) {
		maybeFail();	
		Project project = service.getProjectById(id); 		
		if (project == null) {
			return "没有找到对应的项目！";
		}
		return  "项目名称："+project.name +", 项目描述：" + project.description;		
	}

	// 引入一些人为的故障
	private void maybeFail() {
		final Long invocationNumber = circuitBreakerCounter .getAndIncrement();
		if (invocationNumber % 4 > 1) {
			// 交替2次成功调用和2次失败调用
			LOGGER.errorf("Invocation #%d failing", invocationNumber);
			throw new RuntimeException("Service failed.");
		}
		LOGGER.infof("Invocation #%d OK", invocationNumber);
	}
	
	
	// ********Quarkus隔离的实现*******
	
	@GET
	@Path("/bulkhead/{id}")
	@Produces(MediaType.TEXT_PLAIN)
    public String bulkhead(@PathParam("id") int id) {
	    return getBulkhead(id);
    }
	
	@Bulkhead(3) 
	private String getBulkhead(int id) {
		try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
		Project project = service.getProjectById(id);
		if (project == null) {
			return "没有找到对应的项目！";
		}
		return  "项目名称："+project.name +", 项目描述：" + project.description;
	}
	
	
	// ********用于ProjectResourceTest的内容*******
	public void setFailRatio(Float failRatio) {
        this.failRatio = failRatio;
    }

	public void resetCounter() {
        this.counter.set(0);
    }

	public Long getCounter() {
        return counter.get();
    }
	
	@POST
	public Response add(Project project) {
		if (project == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		service.add(project);
		return Response.ok(project).build();
	}
	
	@PUT
	public Response update(Project project) {
		if (project == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		service.update(project);
		return Response.ok(project).build();
	}

	@DELETE
	public Response delete(Project project) {
		if (project == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		service.delete(project);
		return Response.ok(project).build();
	}

}