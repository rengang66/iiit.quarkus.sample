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
package com.iiit.quarkus.sample.gof23.structuralpattern.adapter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Designer {
	
	private String designerName ;
	private String works = null;
	private String Designs = null; 
	
	public Designer(){		
	}
	
	public Designer(String name){
		designerName = name;
	}
	
	public String getFinishWork(){		
		designToWorks();
		return works;
	}
	
	public void setDesign(String Design){
		Designs  = Design;
		}
	
	public void designToWorks(){
		System.out.println("————按照需求分析设计转化为具体工作————");
		works = Designs +getName()+"按照需求设计内容，完成工作。";
	}

	public String getName() {
		return designerName;
	}

	public void setName(String name) {
		this.designerName = name;
	}

	public String getWorks() {
		return works;
	}	

	public String getDesigns() {
		return Designs;
	}

	public void setDesigns(String designs) {
		Designs = designs;
	}

}
