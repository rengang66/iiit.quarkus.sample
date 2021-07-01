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
package com.iiit.quarkus.sample.gof23.behavioralpattern.template;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectA extends ProjectProcessTemplate implements ProjectProcess{

	public void doActualWork(){		
		feasibilityProcess("定制可行性研究");		
		technicalDiscussionProcess("定制技术交流");		
		bidProcess("定制投标");		
		requirementProcess("定制需求");
		designProcess("定制设计");
		programProcess("定制编码");		
		testProcess("定制测试");		
		deploymentProcess("定制部署");		
		maintenanceProcess("定制维护");			
	}
}
