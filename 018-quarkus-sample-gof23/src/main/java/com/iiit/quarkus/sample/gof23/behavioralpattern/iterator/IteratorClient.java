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
package com.iiit.quarkus.sample.gof23.behavioralpattern.iterator;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class IteratorClient implements QuarkusApplication{

	@ConfigProperty(name = "gof23.behavioralpattern.iterator.title", defaultValue = "gof23")
	String title;
	
	@Inject
	Employee employee1,employee2,employee3,employee4,employee5;
	
	@Inject
	EmployeeCollection employeeCollection;
	
	@Inject
	ImplementIterator iterator;
	
	@Override
	public int run(String... args){
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	

		Employee employee;		
		
		employee1.setEmployeeName("小王");
		employee1.setEducation("学士");
		
		employee2.setEmployeeName("小张");
		employee2.setEducation("学士");
		
		employee3.setEmployeeName("小刘");
		employee3.setEducation("硕士");
		
		employee4.setEmployeeName("小李");
		employee4.setEducation("学士");
		
		employee5.setEmployeeName("小马");
		employee5.setEducation("硕士");
		

		//EmployeeCollection employeeCollection = new EmployeeCollection();

		employeeCollection.addEmployee(employee1).addEmployee(employee2);
		employeeCollection.addEmployee(employee3).addEmployee(employee4);
		employeeCollection.addEmployee(employee5);

		//ImplementIterator iterator = new ImplementIterator(employeeCollection);
		iterator.setEmployeeCollection(employeeCollection);

		do {
			employee = iterator.next();
			if (employee != null) {
				if (employee.getEducation().equals("硕士")){
					System.out.println(employee.getEmployeeName()+";");
				}
			}
		} while (employee != null);
		
		return 0;
	}
	
	public static void main(String... args) {
		Quarkus.run(IteratorClient.class, args);
	}

}
