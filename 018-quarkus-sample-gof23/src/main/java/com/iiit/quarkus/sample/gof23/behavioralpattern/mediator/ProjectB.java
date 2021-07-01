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
package com.iiit.quarkus.sample.gof23.behavioralpattern.mediator;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectB extends AbstractProject {

	public ProjectB(){
		projectName = "ProjectB";
	}
	
	public ProjectB(Map<String, Member> members, Map<String, Task> tasks) {
		initizeMember(members);
		initizeTask(tasks);
		projectName = "ProjectB";
	}
	
	public ProjectB setMembers(Map<String,Member> members){
		initizeMember(members);
		return this;
	}
	
	public ProjectB setTasks(Map<String,Task> tasks){
		initizeTask(tasks);
		return this;
	}
	
	
}
