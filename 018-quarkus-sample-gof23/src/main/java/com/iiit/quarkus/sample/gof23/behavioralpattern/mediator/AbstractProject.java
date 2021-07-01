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

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public abstract class AbstractProject {
	
	protected String projectName ;
	protected TechnicalDirector leader;
	protected Map<String,Member> memberMap = new HashMap<String,Member>();
	protected Map<String,Task> taskMap = new HashMap<String,Task>();
	
	protected void initizeTask(Map<String,Task> map){
		taskMap = map;
	}
	
	protected void initizeMember(Map<String,Member> map){
		memberMap = map;
	}
	
	public void addMember(Member member){
		memberMap.put(member.getMemberName(), member);
	}
	public void removeMember(Member member){
		memberMap.remove(member.getMemberName());
	}
	
	public void addTask(Task task){
		taskMap.put(task.getTaskName(), task);
	}
	
	public void reduceTask(Task task){
		taskMap.remove(task.getTaskName());
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public TechnicalDirector getLeader() {
		return leader;
	}
	public void setLeader(TechnicalDirector leader) {
		this.leader = leader;
	}
	
	public void showProjectContent(){
		System.out.println("显示"+getProjectName()+"项目成员和任务情况：");
		System.out.print("项目成员:");
		String memberName;
		//Iterator it = memberMap.keySet().iterator();
		Iterator<String> it = memberMap.keySet().iterator();
		while (it.hasNext()){
			memberName = (String)it.next();			
			System.out.print(memberName+";");
		}
		System.out.println();
		
		String taskName;
		System.out.print("项目任务:");
		it = taskMap.keySet().iterator();
		while (it.hasNext()){
			taskName = (String)it.next();			
			System.out.print(taskName+";");
		}
		System.out.println();
		
	}

}
