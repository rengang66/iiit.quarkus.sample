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
package com.iiit.quarkus.sample.orm.panache.activerecord;

import io.quarkus.panache.common.Sort;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("projects")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProjectResource {

    private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());   

    public ProjectResource(){}
    
    //获取Project列表
    @GET
    public List<Project> get() {    	
    	return Project.listAll(Sort.by("name"));       
    }

    //获取单个Project信息
    @GET
    @Path("{id}")
    public Project getById(@PathParam("id") Long id) {     	
    	Project entity = Project.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Project with id of " + id + " does not exist.", 404);
        }
        return entity;    	
    }

    //增加一个Project对象
    @POST
    @Transactional
    public Response add( Project project) {  
    	/*
    	if (project.id == null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        */
    	project.persist();
        return Response.ok(project).status(201).build();
     }

    //修改一个Project对象
    @PUT
    @Path("{id}") 
    @Transactional
    public Project update(@PathParam("id") Long id, Project project) {    	
    	if (project.getName() == null) {
            throw new WebApplicationException("Project Name was not set on request.", 422);
        }
    	Project entity = Project.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Project with id of " + id + " does not exist.", 404);
        }
        entity.setName(project.getName()); 
        return entity;
    }   

    //删除一个Project对象
    @DELETE
    @Path("{id}") 
    @Transactional
    public Response delete( @PathParam("id") Long id ) {    	
    	Project entity = Project.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Project with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

    // 处理Response的错误情况
    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {
        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            JsonObjectBuilder entityBuilder = Json.createObjectBuilder()
                    .add("exceptionType", exception.getClass().getName())
                    .add("code", code);

            if (exception.getMessage() != null) {
                entityBuilder.add("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(entityBuilder.build())
                    .build();
        }
    }
}
