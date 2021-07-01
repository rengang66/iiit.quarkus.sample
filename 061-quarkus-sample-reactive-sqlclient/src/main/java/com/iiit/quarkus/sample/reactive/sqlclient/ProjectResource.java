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
package com.iiit.quarkus.sample.reactive.sqlclient;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
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
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);
		
	
	// 注入ReactiveProjectService对象
	@Inject
	ProjectService reativeService;

	public ProjectResource() {
	}
		
	// 获取所有的Project对象，形成列表返回
	@GET
	@Path("/reactive")
	public Multi<Project> listReative() {
		LOGGER.info("获取所有的Project对象，形成列表返回");
		return reativeService.findAll();
	}
	
	//获取过滤出来的一个Project对象，兵返回Project对象
	@GET   
    @Path("/reactive/{id}")
	public Uni<Project> getReativeProject(@PathParam("id")  long id) {		
        return reativeService.findById(id);		
	}
	
	//提交新增一个新的Project对象，
	@POST
	@Path("/reactive/add")
	public Uni<Long> add(Project project){
		return reativeService.add(project);
	}
	
	//提交修改一个Project对象，
	@PUT
	@Path("/reactive/update")
	public Uni<Boolean> update(Project project){
		return reativeService.update(project);
	}
	
	//根据Project对象的主键，提交删除该Project对象
	@DELETE
	@Path("/reactive/delete/{id}")
	public Uni<Boolean> delete(@PathParam("id") Long id){		
		return reativeService.delete(id);
	}
}