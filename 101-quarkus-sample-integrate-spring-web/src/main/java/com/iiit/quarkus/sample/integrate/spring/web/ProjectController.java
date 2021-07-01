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
package com.iiit.quarkus.sample.integrate.spring.web;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectController.class);
		
	@Inject
	ProjectService service;
	
	@GetMapping()	
	public List<Project> list() {
		LOGGER.info("获取所有数据");
		return service.getAllProject();
	}
	
	
	@GetMapping("/{id}")    
	public Response getById(@PathVariable(name = "id")  int id) {        	
        Project project = service.getProjectById(id);        
        if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(project).build();		
	}
    
	
	@RequestMapping("/add")	
	public Response add(@RequestBody Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		service.add(project);
		return Response.ok(project).build();
	}

	
	@RequestMapping("/update")	
	public Response update(@RequestBody Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		service.update(project);
		return Response.ok(project).build();
	}

	
	@RequestMapping("/delete")	
	public Response delete(@RequestBody Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		service.delete(project);
		return Response.ok(project).build();
	}
}