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
package com.iiit.quarkus.sample.vertx.routes;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.vertx.ext.web.Router;

import com.iiit.quarkus.sample.vertx.data.ProjectService;

@ApplicationScoped
public class ProjectRouteRegistar {

	@Inject
	ProjectService service;
	
    public void init(@Observes Router router) {
        router.get("/route/registar").handler(rc -> {
        	String content = service.getProjectInform();        	
        	System.out.println(content);
        	rc.response().end(content);        	
        });
    }
}
