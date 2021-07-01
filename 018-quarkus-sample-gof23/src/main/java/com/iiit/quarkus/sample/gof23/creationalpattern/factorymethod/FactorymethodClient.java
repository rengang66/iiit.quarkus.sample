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
package com.iiit.quarkus.sample.gof23.creationalpattern.factorymethod;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

// @QuarkusMain
public class FactorymethodClient implements QuarkusApplication{	
	
	@ConfigProperty(name = "gof23.creationalpattern.factorymethod.title", defaultValue = "gof23")
	String title;
	
	@Inject
	CompanyA company;
	
	@Override
    public int run(String... args){		
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	

		// 根据传入的参数得到ProductA产品		
		Product product = company.bulidProduct("A");
		product.doUse();

		// 根据传入的参数得到ProductB产品
		product = company.bulidProduct("B");
		product.doUse();
		 return 0;
	}
	
	public static void main(String... args) {		
		Quarkus.run(FactorymethodClient.class, args);		
	}	

}
