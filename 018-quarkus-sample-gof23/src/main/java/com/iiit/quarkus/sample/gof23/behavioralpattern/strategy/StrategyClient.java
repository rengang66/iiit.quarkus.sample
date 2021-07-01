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
package com.iiit.quarkus.sample.gof23.behavioralpattern.strategy;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;


public class StrategyClient  implements QuarkusApplication{
	
	@ConfigProperty(name = "gof23.behavioralpattern.strategy.title", defaultValue = "gof23")
	String title;
	
	@Inject
	Project projectA,projectB,projectC;
	
	@Inject
	BankStrategy strategy1;
	
	@Inject
	GovernmentStrategy strategy2;
	
	@Inject
	TelecomStrategy strategy3;
	
	@Override
	public int run(String... args)  {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
		
		System.out.println("——————要求projectA采用银行策略——————");		
		projectA.setProjectName("projectA");		
		
		projectA.setStrategy(strategy1);
		projectA.doStrategyWork();
		
		System.out.println("——————要求projectB采用政府策略——————");		
		projectB.setProjectName("projectB");		
		projectB.setStrategy(strategy2);
		projectB.doStrategyWork();
		
		System.out.println("——————要求projectC采用电信策略——————");		
		projectC.setProjectName("projectC");		
		projectC.setStrategy(strategy3);
		projectC.doStrategyWork();
		
		return 0;

	}
	
	public static void main(String... args) {
		Quarkus.run(StrategyClient.class, args);
	}

}
