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
package com.iiit.quarkus.sample.integrate.spring.security;

import java.util.List;
import javax.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectController.class);
		
	private final ProjectService service;

	public ProjectController( ProjectService service1 ) {
		this.service = service1;
	}

	@Secured("admin")
	@GetMapping
	public List<Project> list() {
		LOGGER.info("admin授权通过");
		return service.getAllProject();
	}
	
	@Secured("user")
	@GetMapping("/{id}") 
	public Response get(@PathVariable(name = "id")  int id) {
		LOGGER.info("user授权通过");
        Project project = service.getProjectById(id);        
        if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(project).build();		
	}   
    
	@RequestMapping("/add")	
	public Response add(Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		service.add(project);
		return Response.ok(project).build();
	}

	@RequestMapping("/update")	
	public Response update(Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		service.update(project);
		return Response.ok(project).build();
	}

	@RequestMapping("/delete")	
	public Response delete(Project project) {		
		if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
		service.delete(project);
		return Response.ok(project).build();
	}	
	

}