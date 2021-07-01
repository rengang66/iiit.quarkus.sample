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
package com.iiit.quarkus.sample.reactive.mutiny;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);	
	
	private Map<Integer, Project> projectMap = new HashMap<>();  

	public ProjectService() {		
		projectMap.put(1, new Project(1, "项目A", "关于项目A的情况描述"));
		projectMap.put(2, new Project(2, "项目B", "关于项目B的情况描述"));
		//projectMap.put(3, new Project(3, "项目C", "关于项目C的情况描述"));        	
	}
	
	
	// Multi形成List列表
	public Multi<List<Project>> getProjectList() {
    	//return Multi.createFrom().items(service.getAllProject());
    	//return new ArrayList<>(projectMap.values());
		LOGGER.info("Multi形成List列表");
    	return Multi.createFrom().items(new ArrayList<>(projectMap.values()));
    }
	
	// Uni形成Project对象
	public Uni<Project> getProjectById(Integer id) {
		LOGGER.info("Uni形成Project对象");
		Project project = projectMap.get(id);
        return Uni.createFrom().item(project);
    }

	// Uni形成Project的格式化字符
    public Uni<String> getProjectNameById(Integer id) { 
    	LOGGER.info("Uni形成Project的格式化字符");
    	Project project = projectMap.get(id);
        return Uni.createFrom().item(project)
                .onItem().transform(n -> String.format("项目名称： %s",project.name+",项目描述："+ project.description ));
    }
    
    // Uni获得Project的name字符
    public Uni<String> getNameById(Integer id) {    	
    	//Project project = service.getProjectById(id);
    	Project project = projectMap.get(id);
        return Uni.createFrom().item(project)
                .onItem().transform(n -> {
                	String name = project.name;
                	return name;
                	});        
    }
    
    // Multi形成Project对象的响应次数
    public Multi<String> getProjectNameCountById(int count, Integer id) {    	
    	Project project = projectMap.get(id);
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> String.format("项目名称： %s - %d",project.name, n))
                .transform().byTakingFirstItems(count);    
    }
    
    
    public Multi<List<Project>> add( Project project) {		
		projectMap.put(projectMap.size()+1,project);
		return Multi.createFrom().items(new ArrayList<>(projectMap.values()));
	}

	public Multi<List<Project>> update(Project project) {		
		if (projectMap.containsKey(project.id))	{
			projectMap.replace(project.id, project);			
		}		
		return Multi.createFrom().items(new ArrayList<>(projectMap.values()));
	}

	public Multi<List<Project>> delete(Project project) {
		if (projectMap.containsKey(project.id))	{
			projectMap.remove(project.id);			
		}
		return Multi.createFrom().items(new ArrayList<>(projectMap.values()));
	}    
    
}
