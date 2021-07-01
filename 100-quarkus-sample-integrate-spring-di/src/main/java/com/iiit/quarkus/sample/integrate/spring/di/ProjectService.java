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
package com.iiit.quarkus.sample.integrate.spring.di;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);
	
	@Autowired
    @Qualifier("projectStateFunction")
	ProjectFunction projectState;
	
	@Autowired
    @Qualifier("projectCapitalizeFunction")
	ProjectFunction capitalizerStringFunction;
	
	@Autowired
	MessageBuilder  messageBuilder;
	
	@Value("${project.changeitem}")
    String changItem;
	
	private Map<Integer, Project> projectMap = new HashMap<>();  

	public ProjectService() {
		LOGGER.info("初始化数据");
		projectMap.put(1, new Project(1, "项目A", "关于项目A的情况描述"));
		projectMap.put(2, new Project(2, "项目B", "关于项目B的情况描述"));
		projectMap.put(3, new Project(3, "项目C", "关于项目C的情况描述"));        	
	}	

	public List<Project> getAllProject() {
		return new ArrayList<>(projectMap.values());
	}
	
	public Map<Integer, Project> getProjectHashMap(){
		return projectMap;
	}
	
	public Project getProjectById(Integer id) {
        return projectMap.get(id);
    }
	
	public Project getProjectStateById(Integer id) {
		Project project = projectMap.get(id);
		String isTrue = String.valueOf(project.state);		
		project.state = Boolean.valueOf(projectState.apply(isTrue));		
        return project;
    }

	public List<Project> add(Project project) {		
		projectMap.put(projectMap.size()+1,project);
		return new ArrayList<>(projectMap.values());
	}

	public List<Project> update(Project project) {
		
		if (projectMap.containsKey(project.id))	{
			projectMap.replace(project.id, project);			
		}		
		return new ArrayList<>(projectMap.values());
	}

	public List<Project> delete(Project project) {
		if (projectMap.containsKey(project.id))	{
			projectMap.remove(project.id);			
		}		
		return new ArrayList<>(projectMap.values());
	}
	
	public String getMessage(){
		 return messageBuilder.getMessage();
	}
	
	public String getChange(){		
		 return capitalizerStringFunction.apply(changItem);		 
	}
	
}