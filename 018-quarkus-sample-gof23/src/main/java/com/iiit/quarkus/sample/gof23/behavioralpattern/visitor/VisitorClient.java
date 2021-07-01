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
package com.iiit.quarkus.sample.gof23.behavioralpattern.visitor;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class VisitorClient  implements QuarkusApplication {
	
	@ConfigProperty(name = "gof23.behavioralpattern.visitor.title", defaultValue = "gof23")
	String title;
	
	@Inject
	CompanyA company1;
	
	@Inject
	CompanyB company2;
	
	@Inject
	AccountingFirm accountingFirm;
	
	@Inject
	TaxBureau taxBureau;
	
	@Inject
	TradeBureau tradeBureau;
	
	@Override
	public int run(String... args){
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
				
		System.out.println("————对CompanyA类公司进行处理————");
		company1.Accept(accountingFirm);		
		company1.doVisit();
		
		company1.Accept(taxBureau);	
		company1.doVisit();	
		
		company1.Accept(tradeBureau);		
		company1.doVisit();	
		
		System.out.println("————对CompanyB类公司进行处理————");
		company2.Accept(accountingFirm);		
		company2.doVisit();
		
		company2.Accept(taxBureau);		
		company2.doVisit();	
		
		company2.Accept(tradeBureau);		
		company2.doVisit();	
		
		return 0;
	}
	
	public static void main(String... args) {
		Quarkus.run(VisitorClient.class, args);
	}

}
