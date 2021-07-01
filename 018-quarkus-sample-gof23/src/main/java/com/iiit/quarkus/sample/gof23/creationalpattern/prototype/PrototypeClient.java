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
package com.iiit.quarkus.sample.gof23.creationalpattern.prototype;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class PrototypeClient implements QuarkusApplication {

	@ConfigProperty(name = "gof23.creationalpattern.prototype.title", defaultValue = "gof23")
	String title;
	
	@Inject
	CompanyBaseIntroduction company ;
	
	@Inject
	AbstractPrototype departA ;
	
	@Inject
	AbstractPrototype company2 ;	
	
	@Override
    public int run(String... args){
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
		
		departA = company.cloneMyself();

		departA.addSomeIntrouction("部门A介绍", "这是部门A介绍的内容");	

		AbstractPrototype departB = departA.cloneMyself();
		departB.deleteSomeIntrouction("部门A介绍");
		departB.addSomeIntrouction("部门B介绍", "这是部门B介绍的内容");
		departB.showIntrouction();
		return 0;

	}
	
	public static void main(String... args) {		
		Quarkus.run(PrototypeClient.class, args);		
	}

}
