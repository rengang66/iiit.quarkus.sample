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
package com.iiit.quarkus.sample.gof23.behavioralpattern.interpreter;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class InterpreterClient implements QuarkusApplication {

	@ConfigProperty(name = "gof23.behavioralpattern.interpreter.title", defaultValue = "gof23")
	String title;
	
	@Inject
	Project project1;
	
	@Inject
	FinancialDepExpression financialDepExpression;
	
	@Inject
	MarketDepExpression marketDepExpression;
	
	@Inject
	TechnicalDepExpression technicalDepExpression;
	
	@Override
	public int run(String... args){
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
				
		project1.setProjectName("ProjectA");
		List<AbstractExpression> expressList = new ArrayList<AbstractExpression>();
	
		expressList.add(financialDepExpression);
		expressList.add(marketDepExpression);
		expressList.add(technicalDepExpression);

		for (int i = 0; i < expressList.size(); i++) {
			expressList.get(i).Interpret(project1);
		}		
		return 0;		
	}
	
	public static void main(String... args) {
		Quarkus.run(InterpreterClient.class, args);
	}
}
