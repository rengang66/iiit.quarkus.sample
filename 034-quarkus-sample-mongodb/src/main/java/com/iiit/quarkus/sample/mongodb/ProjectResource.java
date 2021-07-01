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
package com.iiit.quarkus.sample.mongodb;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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

	// 获取所有的Project对象，形成List
	@GET
	public List<Project> list() {
		return service.list();
	}

	@GET
	@Path("/find/{name}")
	public List<Project> getByName(@PathParam("name") String name) {
		String projectName = "项目"+name;		
		return service.get(projectName);
	}

	// 提交并新增一个project对象
	@POST
	public List<Project> add(Project project) {
		service.add(project);
		return list();
	}

	// 提交并修改一个project对象
	@PUT
	public List<Project> update(Project project) {
		service.update(project);
		return list();
	}

	// 提交并删除一个project对象
	@DELETE
	public List<Project> delete(Project project) {
		service.delete(project);
		return list();
	}
}