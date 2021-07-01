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
package com.iiit.quarkus.sample.gof23;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iiit.quarkus.sample.gof23.structuralpattern.adapter.AdapterClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.bridge.BridgeClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.composite.CompositeClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.decorator.DecoratorClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.facade.FacadeClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.flyweight.FlyweightClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.proxy.ProxyClient;

@Path("/gof23")
public class Gof23StructuralPatternSource {	

    @Inject	AdapterClient adapterClient;
	
	@Inject	BridgeClient bridgeClient;
	
	@Inject	CompositeClient compositeClient;
	
	@Inject	DecoratorClient decoratorClient;
	
	@Inject	FacadeClient facadeClient;
	
	@Inject	FlyweightClient flyweightClient;
	
	@Inject	ProxyClient proxyClient;

	public Gof23StructuralPatternSource() {
	}

	@GET
	@Path("/adapter")
	@Produces(MediaType.TEXT_PLAIN)
	public String adapterClient() {
		adapterClient.run("adapterClient");
		return "adapter is running OK;";
	}

	@GET
	@Path("/bridge")
	@Produces(MediaType.TEXT_PLAIN)
	public String bridgeClient() {
		bridgeClient.run("bridgeClient");
		return "bridge is running OK;";
	}
	
	@GET
	@Path("/composite")
	@Produces(MediaType.TEXT_PLAIN)
	public String compositeClient() {
		compositeClient.run("compositeClient");
		return "composite is running OK;";
	}
	
	@GET
	@Path("/decorator")
	@Produces(MediaType.TEXT_PLAIN)
	public String decoratorClient() {
		decoratorClient.run("decoratorClient");
		return "decorator is running OK;";
	}
	
	@GET
	@Path("/facade")
	@Produces(MediaType.TEXT_PLAIN)
	public String facadeClient() {
		facadeClient.run("facadeClient");
		return "facade is running OK;";
	}
	
	@GET
	@Path("/flyweight")
	@Produces(MediaType.TEXT_PLAIN)
	public String flyweightClient() {
		flyweightClient.run("flyweightClient");
		return "flyweight is running OK;";
	}
	
	@GET
	@Path("/proxy")
	@Produces(MediaType.TEXT_PLAIN)
	public String proxyClient() {
		proxyClient.run("proxyClient");
		return "proxy is running OK;";
	}
	

}