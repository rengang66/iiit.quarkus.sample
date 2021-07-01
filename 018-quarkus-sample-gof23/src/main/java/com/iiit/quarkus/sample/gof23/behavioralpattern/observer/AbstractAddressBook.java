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

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAddressBook {
	
	protected List<AbstractEmployee> employeeList = new ArrayList<AbstractEmployee>();
	protected  String addressBook;
	
	public String getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(String addressBook) {
		this.addressBook = addressBook;
	}
	
	public void addEmployee(AbstractEmployee employee){
		employeeList.add(employee);
	}
	
	public void removeEmployee(AbstractEmployee employee){
		employeeList.remove(employee);
	}
	
	public void notice(){		
		
		
	}

	

}
