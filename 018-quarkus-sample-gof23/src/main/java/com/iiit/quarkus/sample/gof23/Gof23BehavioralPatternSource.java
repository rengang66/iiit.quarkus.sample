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

import com.iiit.quarkus.sample.gof23.behavioralpattern.chainofresponsibility.ChainofresponsibilityClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.command.CommandClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.interpreter.InterpreterClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.iterator.IteratorClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.mediator.MediatorClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.memento.MementoClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.observer.ObserverClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.state.StateClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.strategy.StrategyClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.template.TemplateClient;
import com.iiit.quarkus.sample.gof23.behavioralpattern.visitor.VisitorClient;


@Path("/gof23")
public class Gof23BehavioralPatternSource {	
   
    @Inject	ChainofresponsibilityClient  chainofresponsibilityClient;
	
	@Inject	CommandClient  commandClient;
	
	@Inject	InterpreterClient  interpreterClient;
	
	@Inject	IteratorClient  iteratorClient;
	
	@Inject	MediatorClient  mediatorClient;
	
	@Inject	MementoClient  mementoClient;
	
	@Inject	ObserverClient  observerClient;
	
	@Inject	StateClient  stateClient;
	
	@Inject	StrategyClient  strategyClient;
	
	@Inject	TemplateClient  templateClient;
	
	@Inject	VisitorClient  visitorClient;

	public Gof23BehavioralPatternSource() {
	}

	@GET
	@Path("/chainofresponsibility")
	@Produces(MediaType.TEXT_PLAIN)
	public String chainofresponsibilityClient() {
		chainofresponsibilityClient.run("chainofresponsibilityClient");
		return "chainofresponsibility is running OK;";
	}

	@GET
	@Path("/command")
	@Produces(MediaType.TEXT_PLAIN)
	public String commandClient() {
		commandClient.run("commandClient");
		return "command is running OK;";
	}
	
	@GET
	@Path("/interpreter")
	@Produces(MediaType.TEXT_PLAIN)
	public String interpreterClient() {
		interpreterClient.run("interpreterClient");
		return "interpreter is running OK;";
	}
	
	@GET
	@Path("/iterator")
	@Produces(MediaType.TEXT_PLAIN)
	public String iteratorClient() {
		iteratorClient.run("iteratorClient");
		return "iterator is running OK;";
	}
	
	@GET
	@Path("/mediator")
	@Produces(MediaType.TEXT_PLAIN)
	public String mediatorClient() {
		mediatorClient.run("mediatorClient");
		return "mediator is running OK;";
	}
	
	@GET
	@Path("/memento")
	@Produces(MediaType.TEXT_PLAIN)
	public String mementoClient() {
		mementoClient.run("mementoClient");
		return "memento is running OK;";
	}
	
	@GET
	@Path("/observer")
	@Produces(MediaType.TEXT_PLAIN)
	public String observerClient() {
		observerClient.run("observerClient");
		return "observer is running OK;";
	}
	
	@GET
	@Path("/state")
	@Produces(MediaType.TEXT_PLAIN)
	public String stateClient() {
		stateClient.run("stateClient");
		return "state is running OK;";
	}
	
	@GET
	@Path("/strategy")
	@Produces(MediaType.TEXT_PLAIN)
	public String strategyClient() {
		strategyClient.run("strategyClient");
		return "strategy is running OK;";
	}
	
	@GET
	@Path("/template")
	@Produces(MediaType.TEXT_PLAIN)
	public String templateClient() {
		templateClient.run("templateClient");
		return "template is running OK;";
	}
	
	@GET
	@Path("/visitor")
	@Produces(MediaType.TEXT_PLAIN)
	public String visitorClient() {
		visitorClient.run("visitorClient");
		return "visitor is running OK;";
	}

}