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
package com.iiit.quarkus.sample.openapi.swaggerui;

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


@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

	// 注入ProjectService对象
		@Inject
		ProjectService service;

		public ProjectResource() {
		}

		@GET
		public Set<Project> list() {
			return service.list();
		}

		@GET
		@Path("/{key}")
		public Set<Project> get(@PathParam("key") String key) {
			return service.list();
		}

		@POST
		public Set<Project> add(Project project) {
			return service.add(project);
		}

		@PUT
		public Set<Project> update(Project project) {
			return service.update(project);
		}

		@DELETE
		public Set<Project> delete(Project project) {
			return service.delete(project);
		}
    
}