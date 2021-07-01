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
package com.iiit.quarkus.sample.vertx.json;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import com.iiit.quarkus.sample.vertx.data.ProjectService;

@Path("/vertx/json")
@Produces(MediaType.APPLICATION_JSON)
public class VertxJsonResource {
	
	@Inject
	ProjectService service;

    @GET
    @Path("/object/{name}")
    public JsonObject jsonObject(@PathParam String name) {    	
        return new JsonObject().put("Hello", name);
    }

    @GET
    @Path("/array/{name}")
    public JsonArray jsonArray(@PathParam String name) {
        return new JsonArray().add("Hello").add(name);
    }
}
