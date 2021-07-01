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
package com.iiit.quarkus.sample.gof23.behavioralpattern.state;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class StateClient implements QuarkusApplication{
	
	@ConfigProperty(name = "gof23.behavioralpattern.state.title", defaultValue = "gof23")
	String title;
	
	@Inject
	ProjectBuilderState bullder;
	
	@Inject
	ProjectDevelopmentState development;
	
	@Inject
	ProjectRunState run;
	
	@Inject
	ProjectMaintenanceState maintenance;
	
	@Inject
	ProjectEndState end;
	
	@Inject
	Project projectA ;
	
	
	@Override
	public int run(String... args) {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
						
		projectA.setProjectName("projectA");
		
		System.out.println("——————设置项目为立项状态——————");
		projectA.setCurrentState(bullder);
		projectA.doCurrentWork();
		
		System.out.println("——————设置项目为开发状态——————");
		projectA.setCurrentState(development);
		projectA.doCurrentWork();
		
		System.out.println("——————设置项目为试运行状态——————");
		projectA.setCurrentState(run);
		projectA.doCurrentWork();
		
		System.out.println("——————设置项目为维护状态——————");
		projectA.setCurrentState(maintenance);
		projectA.doCurrentWork();
		
		System.out.println("——————设置项目为结项状态——————");
		projectA.setCurrentState(end);
		projectA.doCurrentWork();
		
		return 0;
	}
	
	public static void main(String... args) {
		Quarkus.run(StateClient.class, args);
	}

}
