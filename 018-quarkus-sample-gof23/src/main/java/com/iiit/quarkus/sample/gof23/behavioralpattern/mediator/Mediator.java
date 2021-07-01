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

public abstract class Mediator {
	
	public void changMember(AbstractProject project1,AbstractProject project2,Member member){
		project1.removeMember(member);
		project2.addMember(member);
	}
	
	public void changTask(AbstractProject project1,AbstractProject project2,Task task){
		project1.reduceTask(task);
		project2.addTask(task);
	}
	
	public void doCoordination(){}

}
