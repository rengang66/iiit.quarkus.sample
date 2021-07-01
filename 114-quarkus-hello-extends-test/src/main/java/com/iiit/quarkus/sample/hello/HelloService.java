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
package com.iiit.quarkus.sample.hello;

import javax.enterprise.context.ApplicationScoped;

import com.iiit.quarkus.sample.extension.project.Project;
import com.iiit.quarkus.sample.extension.project.ProjectResource;
import com.iiit.quarkus.sample.extension.project.ProjectService;

@ApplicationScoped
public class HelloService {
	
	
	public String getHello() {
        return "hello world";
    }

    public String getHello(String name) {
        return "hello " + name;
    }
    
    
    public String getProject(){
    	
    	ProjectService service = new ProjectService();   
    	
    	Project project = service.getProjectHashMap().get(1);
    	
    	String projectInform = "项目名称："+project.name+";" +"项目描述"+project.description;
    	
    	return projectInform;
    	
    }
    
    
}
