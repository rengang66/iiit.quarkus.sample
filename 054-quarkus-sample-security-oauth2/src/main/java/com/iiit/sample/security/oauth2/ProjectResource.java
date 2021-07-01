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
package com.iiit.sample.security.oauth2;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.jboss.logging.Logger;

@Path("/projects")
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);

	@Inject
    ProjectService service;
	
    @GET
    @Path("permit-all")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String serveResource(@Context SecurityContext ctx) {
        Principal caller = ctx.getUserPrincipal();
        String name = caller == null ? "anonymous" : caller.getName();
        String helloReply = String.format("hello + %s, isSecure: %s, authScheme: %s", name, ctx.isSecure(),
                ctx.getAuthenticationScheme());       
        System.out.println( helloReply);
        LOGGER.info(helloReply);        
        return service.getProjectInform();
    }

    @GET
    @Path("roles-allowed")
    @RolesAllowed({ "admin" })
    @Produces(MediaType.TEXT_PLAIN)
    public String rolesAllowedResource(@Context SecurityContext ctx) {
        Principal caller = ctx.getUserPrincipal();
        String name = caller == null ? "anonymous" : caller.getName();
        String helloReply = String.format("hello + %s, isSecure: %s, authScheme: %s", name, ctx.isSecure(),
                ctx.getAuthenticationScheme());       
        System.out.println( helloReply);
        LOGGER.info(helloReply);  
        return service.getProjectInform();
    }
}
