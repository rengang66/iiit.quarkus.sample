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
package com.iiit.quarkus.sample.integrate.spring.di;

import java.util.List;

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

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);
		
	// 注入ProjectService对象  //@Inject
	@Autowired	
	ProjectService service;

	public ProjectResource() {
	}

	
	@GET	
	public List<Project> list() {		
		return service.getAllProject();
	}
	
	
    @GET
    @Path("/{id}")
	public Response get(@PathParam("id")  int id) {        	
        Project project = service.getProjectById(id);        
        if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(project).build();		
	}

    @GET
    @Path("/getstate/{id}")
	public Response getState(@PathParam("id")  int id) {        	
        Project project = service.getProjectStateById(id);        
        if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }        
        return Response.ok(project).build();		
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
	
	 @GET
	 @Path("/message")
	 public Response getMessage() {	        
	        return Response.ok(service.getMessage()).build();		
	 }
	 
	 @GET
	 @Path("/change")
	 public Response getChange() {	        
	        return Response.ok(service.getChange()).build();		
	 }

}