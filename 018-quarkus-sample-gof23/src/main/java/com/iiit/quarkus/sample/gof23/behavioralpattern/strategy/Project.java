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
package com.iiit.quarkus.sample.gof23.behavioralpattern.strategy;


import javax.enterprise.context.Dependent;

@Dependent
public class Project {
	
	private String projectName ;
	
	private Strategy strategy ;
	
	public Project() {		
	}
	
	public Project(String projectName) {
		super();
		this.projectName = projectName;
	}

	public Project(String projectName, Strategy strategy) {
		super();
		this.projectName = projectName;
		this.strategy = strategy;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Strategy getStrategy() {
		return strategy;
	}	

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void doCommonWork(){
		
	}
	
	public void doStrategyWork(){
		strategy.doWork(this);
	}
	

}
