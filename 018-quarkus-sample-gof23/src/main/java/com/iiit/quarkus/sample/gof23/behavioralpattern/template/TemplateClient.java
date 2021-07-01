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
package com.iiit.quarkus.sample.gof23.behavioralpattern.template;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;


public class TemplateClient implements QuarkusApplication{
	
	@ConfigProperty(name = "gof23.behavioralpattern.template.title", defaultValue = "gof23")
	String title;
	
	@Inject
	ProjectA  project1;
	
	@Inject
	ProjectB  project2;
	
	@Override
	public int run(String... args) {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
		
		System.out.println("——————projectA的过程——————");		
		project1.doActualWork();
		project1.showProcess();
		
		System.out.println("——————projectB的过程——————");		
		project2.doActualWork();
		project2.showProcess();
		
		return 0;
	}
	
	public static void main(String... args) {
		Quarkus.run(TemplateClient.class, args);
	}

}
