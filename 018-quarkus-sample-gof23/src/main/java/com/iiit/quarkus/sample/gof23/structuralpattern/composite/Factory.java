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
package com.iiit.quarkus.sample.gof23.structuralpattern.composite;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;

@Dependent
public class Factory extends AbstractOrganization{
	private List<AbstractOrganization> componentList = new ArrayList<AbstractOrganization>();
	
	public Factory (String name){		
		super(name);
		organizationType = "Factory";
	}
	
	public Factory (){		
		organizationType = "Factory";
	}
	
	public void add(AbstractOrganization organization){
		componentList.add(organization);
	}
	
	public void showOrganizationStructure(String parentName){
		String organizationName = this.getOrganizationName();
		System.out.println(parentName + organizationName);		
		for (int i=0; i<componentList.size(); i++){
			AbstractOrganization organization = componentList.get(i);
			organization.showOrganizationStructure(parentName+organizationName+"——");
		}		
	}

}
