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

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class MediatorClient implements QuarkusApplication {

	@ConfigProperty(name = "gof23.behavioralpattern.mediator.title", defaultValue = "gof23")
	String title;
	
	@Inject
	Member Programmer1,Programmer2,Designer1,Designer2;
	
	@Inject
	Task ProgramTask1,ProgramTask2,DesignTask1,DesignTask2;
	
	@Inject
	ProjectA projectA;
	
	@Inject
	ProjectB projectB;
	
	@Inject
	TechnicalDirector leader ;
	
	@Override
	public int run(String... args)  {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	

		// 初始化项目人员和任务信息		
		Programmer1.setMemberName("程序员1");
		Programmer2.setMemberName("程序员2");
		Designer1.setMemberName("设计师1");
		Designer2.setMemberName("设计师2");
				
		ProgramTask1.setTaskName("编程工作1");
		ProgramTask2.setTaskName("编程工作2");
		DesignTask1.setTaskName("设计工作1");
		DesignTask2.setTaskName("设计工作2");

		Map<String, Member> ProgrammerMap = new HashMap<String, Member>();
		ProgrammerMap.put(Programmer1.getMemberName(), Programmer1);
		ProgrammerMap.put(Programmer2.getMemberName(), Programmer2);

		Map<String, Member> DesignerMap = new HashMap<String, Member>();
		DesignerMap.put(Designer1.getMemberName(), Designer1);
		DesignerMap.put(Designer2.getMemberName(), Designer2);

		Map<String, Task> ProgramTaskMap = new HashMap<String, Task>();
		ProgramTaskMap.put(ProgramTask1.getTaskName(), ProgramTask1);
		ProgramTaskMap.put(ProgramTask2.getTaskName(), ProgramTask2);

		Map<String, Task> DesignTaskMap = new HashMap<String, Task>();
		DesignTaskMap.put(DesignTask1.getTaskName(), DesignTask1);
		DesignTaskMap.put(DesignTask2.getTaskName(), DesignTask2);
				
		projectA.setMembers(ProgrammerMap).setTasks(ProgramTaskMap);
		projectB.setMembers(DesignerMap).setTasks(DesignTaskMap);

		// 进行项目人员和工作的协调
		System.out.println("—————————协调前的情况—————————");
		projectA.showProjectContent();
		projectB.showProjectContent();

		leader.setProjectA(projectA);
		leader.setProjectB(projectB);

		// 协调两个项目的人员
		leader.changMember(projectA, projectB, Programmer1);
		leader.changMember(projectB, projectA, Designer1);

		// 协调两个项目的任务
		leader.changTask(projectA, projectB, ProgramTask1);
		leader.changTask(projectB, projectA, DesignTask1);

		System.out.println("—————————协调后的情况—————————");
		projectA.showProjectContent();
		projectB.showProjectContent();

		return 0;
	}
	
	public static void main(String... args) {
		Quarkus.run(MediatorClient.class, args);
	}

}
