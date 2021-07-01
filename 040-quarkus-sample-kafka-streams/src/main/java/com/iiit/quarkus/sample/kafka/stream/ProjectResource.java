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
package com.iiit.quarkus.sample.kafka.stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

	private static final Logger LOGGER = Logger
			.getLogger(ProjectResource.class);
	
	@Inject
	ProjectService service;
	
	public ProjectResource(){}		
	
	@GET
	@Path("/commit")
	public String commit() {
		LOGGER.info("提交批量数据");
		service.commit();
		return "OK";
	}	
	
	@GET
	@Path("/producer/{content}")
	public String producer(@PathParam("content")  String content) {
		LOGGER.info("单个提交生产数据");
		service.producer(content);
		return "OK";
	}
	
	@GET
	@Path("/consumer")
	public String consumer() {
		LOGGER.info("消费数据");
		service.consumer();
		return "OK";
	}
		
	@GET
	@Path("/hello")
	public String hello() {		
		return "hello";
	}
	
	@GET
	@Path("/startup")
	public String startup() {
		if ( !ProjectMain.is_startup ){
			service.config();
			ProjectMain.is_startup = true;
		}		
		return "OK";
	}

}