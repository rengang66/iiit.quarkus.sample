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
package com.iiit.quarkus.sample.hello;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class HelloResource {
	
	private static final Logger LOG = Logger.getLogger(HelloResource.class);

	@Inject
	HelloService service;
		 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
    	long executionStart = System.currentTimeMillis();
    	String hello = service.getHello();
    	long executionEnd = System.currentTimeMillis();
    	long  execution = executionEnd - executionStart;    	
    	LOG.infof(hello + execution);
        return hello + execution;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String getHello(@PathParam("name") String name) {
    	return service.getHello(name);
    }
    
}