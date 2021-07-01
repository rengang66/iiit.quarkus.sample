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
package com.iiit.quarkus.sample.vertx.delay;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import com.iiit.quarkus.sample.vertx.data.ProjectService;

import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;

@Path("/vertx/delay")
public class DelayResource {

    @Inject
    Vertx vertx;
    
    @Inject
	ProjectService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Uni<String> greeting(@PathParam("id") Integer id) {    	
        return Uni.createFrom().emitter(emitter -> {
            long start = System.nanoTime();
            //延迟响应 100ms
            vertx.setTimer(100, l -> {
            	String content = service.getProjectInformById(id);
                // 计算已用时间（毫秒）
                long duration = MILLISECONDS.convert(System.nanoTime() - start, NANOSECONDS);                
                String message = "延迟响应："+ duration +"; 获取数据："+ content;
                emitter.complete(message);
            });
        });
    }
}
