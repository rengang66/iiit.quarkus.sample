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
package com.iiit.quarkus.sample.vertx.accessvertx;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import io.smallrye.mutiny.Uni;



@Path("/vertx")
@Produces(MediaType.APPLICATION_JSON)
public class VertxAccessResource {
	
	@Inject io.vertx.core.Vertx vertx;
	
	@Inject io.vertx.mutiny.core.Vertx mutinyVertx;
	
	@Inject io.vertx.reactivex.core.Vertx reactivexVertx;
	
	@Inject io.vertx.axle.core.Vertx axleVertx;
	
	
	@GET
	@Path("/bare")
    public void doVertx() {
		// Bare Vert.x:		
		vertx.fileSystem().readFile("/META-INF/resources/quarkus-introduce.txt", ar -> {
		    if (ar.succeeded()) {		    	
		        System.out.println("文件内容:" + ar.result().toString("UTF-8"));		    	
		    } else {
		        System.out.println("不能打开文件: " + ar.cause().getMessage());
		    }
		});
		
    }
	
	@GET
	@Path("/mutiny")
    public void doMutinyVertx() {
		// Mutiny Vert.x:		
		mutinyVertx.fileSystem().readFile("/META-INF/resources/quarkus-introduce.txt")
		    .onItem().transform(buffer -> buffer.toString("UTF-8"))
		    .subscribe()
		    .with(
		            content -> System.out.println("文件内容: " + content),
		            err -> System.out.println("不能打开文件: " + err.getMessage())
		    );		
	}
	
	@GET
	@Path("/reactivex")
    public void doReactivexVertx() {
		// Rx Java 2 Vert.x		
		reactivexVertx.fileSystem().rxReadFile("/META-INF/resources/quarkus-introduce.txt")
		    .map(buffer -> buffer.toString("UTF-8"))
		    .subscribe(
		            content -> System.out.println("文件内容: " + content),
		            err -> System.out.println("不能打开文件: " + err.getMessage())
		    );
	}
	
	@GET
	@Path("/axle")
    public void doAxleVertx() {
		// Axle API:		
		axleVertx.fileSystem().readFile("/META-INF/resources/quarkus-introduce.txt")
		    .thenApply(buffer -> buffer.toString("UTF-8"))
		    .whenComplete((content, err) -> {
		        if (err != null) {
		            System.out.println("不能打开文件: " + err.getMessage());
		        } else {
		            System.out.println("文件内容: " + content);
		        }
		    });
	}	
	
	@GET	
	@Path("/mutiny/getfile")
    //@Produces(MediaType.TEXT_PLAIN)
    public Uni<String> doSomethingAsync() {
        return mutinyVertx.fileSystem().readFile("/META-INF/resources/quarkus-introduce.txt")
                .onItem().transform(b -> b.toString("UTF-8"));
    }	
}
