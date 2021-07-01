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
package com.iiit.quarkus.sample.opentracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;

@Traced
@ApplicationScoped
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	private Map<Integer, Project> projectMap = new HashMap<>();
  
	public ProjectService() {		
		projectMap.put(1, new Project(1, "项目A", "关于项目A的情况描述"));
		projectMap.put(2, new Project(2, "项目B", "关于项目B的情况描述"));
		projectMap.put(3, new Project(3, "项目C", "关于项目C的情况描述"));        
	}	

	public List<Project> getAllProject() {
		LOGGER.info("ProjectService类的get()生产的日志");
		return new ArrayList<>(projectMap.values());
	}
	
	public Project getProjectById(Integer id) {
		LOGGER.info("ProjectService类的getProjectById(Integer id)生产的日志");
        return projectMap.get(id);
    }

	public List<Project> add(Project project) {		
		projectMap.put(projectMap.size()+1,project);
		LOGGER.info("ProjectService类的add(Project project)生产的日志");
		return new ArrayList<>(projectMap.values());
	}

	public List<Project> update(Project project) {		
		if (projectMap.containsKey(project.id))	{
			projectMap.replace(project.id, project);			
		}
		LOGGER.info("ProjectService类的update(Project project生产的日志");
		return new ArrayList<>(projectMap.values());
	}

	public List<Project> delete(Project project) {
		if (projectMap.containsKey(project.id))	{
			projectMap.remove(project.id);			
		}
		LOGGER.info("ProjectService类的delete(Project project生产的日志");
		return new ArrayList<>(projectMap.values());
	}
}