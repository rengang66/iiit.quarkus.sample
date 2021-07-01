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
package com.iiit.quarkus.sample.reactive.hibernate;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.function.Function;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


@ApplicationScoped
public class ProjectService {

    private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());

    @Inject
    Mutiny.Session mutinySession;
  
    //获取所有Project列表
    public Multi<Project> get() {
        return mutinySession
                .createNamedQuery( "Projects.findAll", Project.class ).getResults();
    }
     
    //获取单个Project
    public Uni<Project> getSingle(Integer id) {    	
    	return mutinySession.find(Project.class, id);    	
    }
  
    //带事务提交增加一条记录   
    public Uni<Response> add(Project project) {
    	
        return mutinySession
                .persist(project)
                .onItem().produceUni(session ->  mutinySession.flush())
               // .onItem().apply(object -> project );
                .onItem().apply(ignore -> Response.ok(project).status(201).build());        
    }     
  
    //带事务提交修改一条记录    
    public Uni<Response> update(Integer id, Project project) {
    	
         // Update function (never returns null)
         Function<Project, Uni<Response>> update = entity -> {
             entity.setName(project.getName());
             return mutinySession.flush()
                     .onItem().apply(ignore -> Response.ok(entity).build());
         };

         return mutinySession
                 .find(Project.class, id )
                       // If entity exists then
                     .onItem().ifNotNull()
                         .produceUni(update)
                     // else
                     .onItem().ifNull()
                         .continueWith(Response.ok().status(404).build());
    	    	
    }

    //带事务提交删除一条记录    
    public Uni<Response> delete( Integer id) {
    	
    	Function<Project, Uni<Response>> delete = entity -> mutinySession.remove(entity)
                .onItem().produceUni(ignore -> mutinySession.flush())
                .onItem().apply(ignore -> Response.ok().status(204).build());

        return mutinySession
                .find( Project.class, id )
                    // If entity exists then
                    .onItem().ifNotNull()
                        .produceUni(delete)
                    // else
                    .onItem().ifNull()
                        .continueWith(Response.ok().status(404).build());    	        
    }
   
}
