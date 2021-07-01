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



// @ApplicationScoped
public class Analyst extends Customer {
	
	private String analystName ;
	private Designer designer = null;
	private String request ;
	
	
	public Analyst(){}
	
	
	public Analyst( String name){
		analystName = name ;
	}
	
	public String getName() {
		return analystName;
	}
	public void setName(String name) {
		this.analystName = name;
	}
	public Designer getDesigner() {
		return designer;
	}
	public void setDesigner(Designer designer) {
		this.designer = designer;
	}
	
	public void setRequest(String content){
		request = content;
	}
	
	public String getFinishworks(){		
		String designs = requestToDesign();
		designer.setDesigns(designs);
		return designer.getFinishWork();
	}
	
	public String requestToDesign(){
		System.out.println("————分析员按照用户需求转化为需求分析和设计————");
		return  getName()+"按照"+request +"，形成需求设计内容。";
	}
	

}
