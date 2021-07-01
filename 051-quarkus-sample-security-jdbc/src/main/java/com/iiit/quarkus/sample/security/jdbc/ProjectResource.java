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
package com.iiit.quarkus.sample.security.jdbc;


import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import org.jboss.logging.Logger;


@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());

	// 注入ProjectService对象
	@Inject
	ProjectService service;

	public ProjectResource() {
	}

	@GET
	@Path("/api/public")    
	@PermitAll
    public List<Project> publicResource() {
		LOGGER.info("public");
		return service.getAllProject();
    }
	
	@GET
	@Path("/api/admin")   
	@RolesAllowed("admin")
    public String adminResource() {
		LOGGER.info("admin");
		return service.getProjectInform();
    }
	
	@GET
    @Path("/api/users/user")
	@RolesAllowed("user")
    public String userResource(@Context SecurityContext securityContext) {
		LOGGER.info(securityContext.getUserPrincipal().getName());
    	return service.getProjectInform();
    }	
	 
}