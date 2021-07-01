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

import javax.enterprise.context.Dependent;

@Dependent
public class WorkTeam extends AbstractOrganization{
	
	public WorkTeam (String name){		
		super(name);
		organizationType = "WorkTeam";
	}
	
	public WorkTeam (){		
		organizationType = "WorkTeam";
	}
	
	public void add(AbstractOrganization c){
		System.out.println("这是叶子节点，下面没有内容");
	}
	
	public void showOrganizationStructure(String parentName){
		String organizationName = this.getOrganizationName();
		System.out.println(parentName+ organizationName);		
	}

}
