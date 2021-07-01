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
package com.iiit.quarkus.sample.gof23.behavioralpattern.observer;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class ObserverClient implements QuarkusApplication {
	
	@ConfigProperty(name = "gof23.behavioralpattern.observer.title", defaultValue = "gof23")
	String title;
	
	@Inject
	CompanyAddressBook addressBook;
	
	@Inject
	CompanyEmployee employee1,employee2;
	
	@Override
	public int run(String... args) {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
		
		System.out.println("——————原有的通讯录——————");
				
		employee1.setEmployeeName("employee1");
		employee2.setEmployeeName("employee2");
		
		addressBook.addEmployee(employee1);
		addressBook.addEmployee(employee2);
		
		System.out.println("——————更新的通讯录——————");
		String newAddressBook = "新的通讯录";
		addressBook.setAddressBook(newAddressBook);
		
		addressBook.notice();	
		
		return 0;

	}
	
	public static void main(String... args) {
		Quarkus.run(ObserverClient.class, args);
	}

}
