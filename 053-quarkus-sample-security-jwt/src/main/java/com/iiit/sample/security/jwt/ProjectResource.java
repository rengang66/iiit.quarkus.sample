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
package com.iiit.sample.security.jwt;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;


@Path("/projects")
@RequestScoped
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);

    @Inject
    JsonWebToken jwt;
    
    @Inject
    @Claim(standard = Claims.birthdate)
    String birthdate;
    
    @Inject
    ProjectService service;

    @GET
    @Path("permit-all")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String serveResource(@Context SecurityContext ctx) {
    	LOGGER.info(getResponseString(ctx));    	
    	return service.getProjectInform();
    }

    @GET
    @Path("roles-allowed")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.TEXT_PLAIN)
    public String rolesAllowedResource(@Context SecurityContext ctx) {
    	LOGGER.info(getResponseString(ctx));
    	LOGGER.info(getResponseString(ctx) + ", birthdate: " + jwt.getClaim("birthdate").toString());
    	return service.getProjectInform();    
    }
    
    @GET
    @Path("roles-allowed-admin")
    @RolesAllowed("Admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String rolesAllowedAdminResource(@Context SecurityContext ctx) {
    	LOGGER.info(getResponseString(ctx));
    	LOGGER.info( getResponseString(ctx) + ", birthdate: " + birthdate);
    	return service.getProjectInform(); 
    }

    @GET
    @Path("deny-all")
    @DenyAll
    @Produces(MediaType.TEXT_PLAIN)
    public String denyResource(@Context SecurityContext ctx) {
        throw new InternalServerErrorException("This method must not be invoked");
    }

    private String getResponseString(SecurityContext ctx) {
    	String name;
        if (ctx.getUserPrincipal() == null) {
            name = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
            name = ctx.getUserPrincipal().getName();
        }
        return String.format("hello + %s,"+ " isHttps: %s,"	+ " authScheme: %s,"
        		+ " hasJWT: %s",name, ctx.isSecure(), 
        		ctx.getAuthenticationScheme(), hasJwt());
    }

	private boolean hasJwt() {
		return jwt.getClaimNames() != null;
	}
}
