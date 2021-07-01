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

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public abstract class ProjectProcessTemplate {
		
	public Map<String,String>  processMap = new HashMap<String,String>();
	
	//可行性分析过程
	public void feasibilityProcess(String content){
		processMap.put("feasibilityProcess", content);
	}
	//技术交流过程
	public void technicalDiscussionProcess(String content){
		processMap.put("technicalDiscussionProcess", content);
	}
	//投标过程
	public void bidProcess(String content){
		processMap.put("bidProcess", content);
	}
	//需求调研和分析过程
	public void requirementProcess(String content){
		processMap.put("requirementProcess", content);
	}
	//设计过程
	public void designProcess(String content){
		processMap.put("designProcess", content);
	}
	//编码过程
	public void programProcess(String content){
		processMap.put("programProcess", content);
	}
	//测试过程
	public void testProcess(String content){
		processMap.put("testProcess", content);
	}
	//部署和实施过程
	public void deploymentProcess(String content){
		processMap.put("deploymentProcess", content);
	}
	//维护过程
	public void maintenanceProcess(String content){
		processMap.put("maintenanceProcess", content);
	}
	
	public void showProcess(){
		String key = null;
		String value = null;		
		Iterator<String> it = processMap.keySet().iterator();
		while (it.hasNext()){
			key = it.next();
			value = processMap.get(key);
			System.out.println("过程: " + key + "; 内容: " + value );
		}
	}
}
