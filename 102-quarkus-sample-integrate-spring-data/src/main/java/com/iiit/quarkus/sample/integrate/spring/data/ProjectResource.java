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
package com.iiit.quarkus.sample.integrate.spring.data;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.Produces;

//import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.logging.Logger;

@Path("/projects")
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);
	
	/*
	private final ProjectRepository projectRepository;

	public ProjectResource( ProjectRepository projectRepository ) {
		this.projectRepository = projectRepository;
	}
	*/
	
	@Inject
	ProjectRepository projectRepository;

	@GET
    @Produces("application/json")
    public Iterable<Project> list() {
        return projectRepository.findAll();
    }
	
	@GET
    @Produces("application/json")	
	@Path("/{id}")
    public Project getById(@PathParam("id") Long id) {
		Optional<Project> optional = projectRepository.findById(id);
		Project project = null;
		if (optional.isPresent()) {
            project = optional.get();            
        }
        return project;
    }
	    

    @POST
    @Path("/add")
    @Produces("application/json")
    @Consumes("application/json")
    public Project add( Project project) { 
    	Optional<Project> optional = projectRepository.findById(project.getId());
    	if (!optional.isPresent()) {
    		 return projectRepository.save(project);
    	}    	
    	throw new IllegalArgumentException("Project with id " + project.getId()+ " exists");
    }

    @PUT
    @Path("/update")
    @Produces("application/json")
    @Consumes("application/json")
    public Project update( Project project) {
        Optional<Project> optional = projectRepository.findById(project.getId());
        if (optional.isPresent()) {            
            return projectRepository.save(project);
        }
        throw new IllegalArgumentException("No Project with id " + project.getId()+ " exists");
    }  
    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id")  long id) {
    	projectRepository.deleteById(id);
    }

}