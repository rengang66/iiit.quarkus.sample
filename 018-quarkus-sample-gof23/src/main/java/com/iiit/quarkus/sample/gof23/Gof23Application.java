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

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iiit.quarkus.sample.gof23.creationalpattern.abstractfactory.AbstractfactoryClient;
import com.iiit.quarkus.sample.gof23.creationalpattern.builder.BuilderClient;
import com.iiit.quarkus.sample.gof23.creationalpattern.factorymethod.FactorymethodClient;
import com.iiit.quarkus.sample.gof23.creationalpattern.prototype.PrototypeClient;
import com.iiit.quarkus.sample.gof23.creationalpattern.singleton.SingletonClient;

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

import com.iiit.quarkus.sample.gof23.structuralpattern.adapter.AdapterClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.bridge.BridgeClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.composite.CompositeClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.decorator.DecoratorClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.facade.FacadeClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.flyweight.FlyweightClient;
import com.iiit.quarkus.sample.gof23.structuralpattern.proxy.ProxyClient;


@Path("/gof23")
@QuarkusMain
public class Gof23Application implements QuarkusApplication {
	
	@Inject	AbstractfactoryClient abstractfactoryClient;
	
	@Inject	BuilderClient builderClient;
	
	@Inject	FactorymethodClient factorymethodClient;
	
	@Inject	PrototypeClient prototypeClient;
	
	@Inject	SingletonClient singletonClient;
	
	
	@Inject	AdapterClient adapterClient;
	
	@Inject	BridgeClient bridgeClient;
	
	@Inject	CompositeClient compositeClient;
	
	@Inject	DecoratorClient decoratorClient;
	
	@Inject	FacadeClient facadeClient;
	
	@Inject	FlyweightClient flyweightClient;
	
	@Inject	ProxyClient proxyClient;
		
	
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
	
	
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String gof23() {
        return "gof23Application";
    }
      
    
    @Override
    public int run(String... args) {        
       System.out.println("Gof23Application is running, wait for input...");        
        Quarkus.waitForExit();
        return 0;
    }
    
    public static void main(String... args) {
		Quarkus.run(Gof23Application.class, args);
	}
    
    void onStart(@Observes StartupEvent ev) {		
		System.out.println("——————————————————gof23Application应用程序输出演示——————————————");
    }

    void onStop(@Observes ShutdownEvent ev) {
    	System.out.println("—————————————————————————————————————————————————————");
    }
}