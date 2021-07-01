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
package com.iiit.quarkus.sample.gof23.structuralpattern.decorator;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;


public class DecoratorClient implements QuarkusApplication {
	
	@ConfigProperty(name = "gof23.structuralpattern.decorator.title", defaultValue = "gof23")
	String title;
	
	@Inject
	StandardProcess projectProcess;
	
	@Inject
	RequestVerificationProcess projectAddProcess1;
	
	@Inject
	DesignCheckProcess  projectAddProcess2;
	
	@Override
	public int run(String... args){
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
		
		System.out.println("——————项目的标准过程———————");
		projectProcess.showAllProcess();
		System.out.println();
		
		//附加需求验证过程
		projectAddProcess1.setActualProcess(projectProcess);
		projectAddProcess1.ConcreteActualProcess();
		
		System.out.println("——————增加需求验证过程后的项目过程———————");
		projectProcess.showAllProcess();
		System.out.println();
		
		//附加设计检查过程
		projectAddProcess2.setActualProcess(projectProcess);
		projectAddProcess2.ConcreteActualProcess();
		
		System.out.println("——————再增加设计检查过程后的项目过程———————");
		projectProcess.showAllProcess();			

		return 0;
	}
	
	public static void main(String... args) {
		Quarkus.run(DecoratorClient.class, args);
	}
	

}
