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
package com.iiit.quarkus.sample.gof23.structuralpattern.bridge;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Meeting extends AbstractAction {
	
	public void doAction(String depart,String title){		
		if (depart.length()==0)	System.out.println("这是部门的会议工作活动");
		System.out.println("这是"+depart+"会议工作活动,"+"主题是"+title);		
	}

}
