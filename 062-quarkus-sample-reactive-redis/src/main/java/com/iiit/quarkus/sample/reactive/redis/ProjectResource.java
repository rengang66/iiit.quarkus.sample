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
package com.iiit.quarkus.sample.reactive.redis;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;


import java.util.List;
import javax.annotation.PostConstruct;
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

	public ProjectResource() {		
    }
	
	//在Redis中初始化数据
    @PostConstruct
    void config() {
    	LOGGER.info("初始化数据");
    	add(new Project("project1", "关于project1的情况描述"));
    	add(new Project("project2", "关于project2的情况描述"));
    }
	
	//获取Service服务对象所有主键列表
	@GET
	public Uni<List<String>> list() {
		return service.keys();
	}
	
	//获取一个主键值对象并提交Service服务对象获取该主键的Project对象
	@GET
	@Path("/{key}")
	public Uni<Project> get(@PathParam("key") String key) {			
		return service.get(key);		
	}

	//获取一个Project对象并提交Service服务对象增加一个Project对象
	@POST
	public Uni<Response> add(Project project) {
		// LOGGER.info("ProjectResource"+"="+ project.name+"---"+project.description);
		return  service.add(project.name, project.description);		
	}	

	//获取一个Project对象并提交Service服务对象修改一个Project对象
	@PUT
	@Path("/{key}")
	public Uni<Response> update(Project project) {
		return service.update(project.name, project.description);
	}

	//获取一个主键值对象并提交Service服务对象并删除该主键的Project对象
	@DELETE
	@Path("/{key}")
	public Uni<Void> delete(@PathParam("key") String key) {
		return service.del(key);
	}

}