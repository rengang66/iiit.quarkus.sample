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
package com.iiit.quarkus.sample.redis;

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

@Path("/projects")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);

	// 注入ProjectService服务对象
	@Inject
	ProjectService service;

	public ProjectResource() {}
	
	//获取一个主键值对象并提交Service服务对象获取该主键的Project对象
	@GET
	@Path("/{key}")
	public Project get(@PathParam("key") String key) {
			return new Project(key, service.get(key));
		}

	//获取一个Project对象并提交Service服务对象增加一个Project对象
	@POST
	public Project add(Project project) {
		LOGGER.info("获取一个Project对象并提交Service服务对象增加一个Project对象");
		service.add(project.name, project.description);
		return project;
	}

	//获取一个Project对象并提交Service服务对象并修改一个Project对象
	@PUT
	@Path("/{key}")
	public void update(Project project) {
		service.update(project.name, project.description);
	}

	//获取一个主键值对象并提交Service服务对象并删除该主键的Project对象
	@DELETE
	@Path("/{key}")
	public void delete(@PathParam("key") String key) {
		service.del(key);
	}

}