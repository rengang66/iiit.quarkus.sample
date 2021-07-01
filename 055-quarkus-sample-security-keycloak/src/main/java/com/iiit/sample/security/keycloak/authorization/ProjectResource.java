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
package com.iiit.sample.security.keycloak.authorization;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.security.identity.SecurityIdentity;


import org.jboss.logging.Logger;

@Path("/projects")
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);

	@Inject
    SecurityIdentity keycloakSecurityContext;
	
	@Inject
    ProjectService service;
	
	public ProjectResource(){}
	
	@GET    
    @Produces(MediaType.APPLICATION_JSON)
    public String inform() {
    	 LOGGER.info("inform is granted"); 
    	 return service.getProjectInform();
    }
    
    @GET
    @Path("/api/public")
    @Produces(MediaType.APPLICATION_JSON)   
    @PermitAll
    public String serveResource() {
    	 LOGGER.info("/api/public"); 
    	return service.getProjectInform();
    }
    
    @GET
    @Path("/api/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String manageResource() {
    	 LOGGER.info("granted"); 
    	 return service.getProjectInform();
    }
    
    @GET
    @Path("/api/users/user")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserResource() {
        return new User(keycloakSecurityContext);
    }
    
    public static class User {
        private final String userName;
        User(SecurityIdentity securityContext) {
            this.userName = securityContext.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    }    
}
