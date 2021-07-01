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
package com.iiit.quarkus.sample.integrate.springcloud.configclient;


import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());

	// 娉ㄥ叆ProjectService瀵硅薄
	@Inject
	ProjectService service;
	
	@ConfigProperty(name = "message", defaultValue="hello default")
    String message;
	
	@ConfigProperty(name = "message1", defaultValue="hello default1")
    String message1;
	
	public ProjectResource() {
	}
	
	@GET
	public Set<Project> list() {
		LOGGER.info("鑾峰彇鎵�鏈夋暟鎹�");
		return service.list();
	}

	@GET
	@Path("{id}")
	public Project getById(@PathParam("id") Integer id) {		
		return service.getById(id);
	}

	@GET
	@Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message;
    }
	
	@GET
	@Path("/hello1")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello1() {
        return message1;
    }

}