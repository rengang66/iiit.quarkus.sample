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
package com.iiit.quarkus.sample.reactive.mutiny;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestSseElementType;

@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);	
	
	// 注入ProjectService对象
	@Inject
	ProjectService reativeService;

	public ProjectResource() {
	}
	
	//获取所有项目的列表
	@GET	
	public Multi<List<Project>> listReative() {		
		return reativeService.getProjectList();
	}
	
	//获取单个项目的信息
	@GET   
    @Path("/{id}")
	public Uni<Project> getReativeProject(  int id) {         
        return reativeService.getProjectById(id);		
	}
	
	//获取单个项目的格式化信息
	@GET   
    @Path("/name/{id}")
	public Uni<String> getReative(int id) {         
        return reativeService.getProjectNameById(id);		
	}  
	
	//获取单个项目的重复信息输出
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{count}/{id}")
	@RestSseElementType(MediaType.TEXT_PLAIN)
    public Multi<String> getProjectName( int count,  int id) {
        return reativeService.getProjectNameCountById(count,  id);
    }
	
	//按照流模式获取单个项目的格式化信息
	@GET
    @Produces(MediaType.SERVER_SENT_EVENTS)  
    @Path("/stream/{count}/{id}")
    public Multi<String> getProjectNameAsStream(int count,  int id) {
        return reativeService.getProjectNameCountById(count,  id);
    }
	
	//获取一个Project对象并提交给Service服务对象实现增加功能
	@POST
	public Multi<List<Project>> add(Project project) {		
		return reativeService.add(project);
	}

	//获取一个Project对象并提交给Service服务对象实现修改功能
	@PUT
	public Multi<List<Project>> update(Project project) {		
		return reativeService.update(project);
	}

	//获取一个Project对象并提交给Service服务对象实现删除功能
	@DELETE
	public Multi<List<Project>> delete(Project project) {		
		return reativeService.delete(project);
	}

}