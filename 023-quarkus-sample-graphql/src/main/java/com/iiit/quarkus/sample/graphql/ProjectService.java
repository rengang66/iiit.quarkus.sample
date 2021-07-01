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
package com.iiit.quarkus.sample.graphql;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;


@ApplicationScoped
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	private Set<Project> projects = Collections.newSetFromMap(Collections
			.synchronizedMap(new LinkedHashMap<>()));

	public ProjectService() {
		LOGGER.info("数据初始化");
		Project project1 = new Project(1,"项目部1", "关于项目部1的描述");
		Project project2 = new Project(2,"项目部2", "关于项目部2的描述");
		
		Project project3 = new Project(3,"项目A", "关于项目A的描述");
		Project project4 = new Project(4,"项目B", "关于项目B的描述");
		
		Project project5 = new Project(5,"项目C", "关于项目C的描述");
		Project project6 = new Project(6,"项目D", "关于项目D的描述");
		
		Project project7 = new Project(7,"项目项AA", "关于项目项AA的描述");
		Project project8 = new Project(8,"项目项AB", "关于项目项AB的描述");
		
		project1.addChildProject(project3);
		project1.addChildProject(project4);
		
		project2.addChildProject(project5);
		project2.addChildProject(project6);
		
		project3.addChildProject(project7);
		project3.addChildProject(project8);
		
		projects.add(project1);
		projects.add(project2);			
		
	}

	public Set<Project> list() {
		return projects;
	}

	public Project getById(Integer id) {		
		for (Project value : projects) {			
			if ( (id.intValue()) == (value.getId().intValue())) {				
				return value;
			}
		}
		return null;
	}
	
	public Set<Project> add(Project project) {
		projects.add(project);
		return projects;
	}

	public Set<Project> update(Project project) {
		projects.removeIf(existingProject -> existingProject.getName()
				.contentEquals(project.getName()));
		projects.add(project);
		return projects;
	}

	public Set<Project> delete(Project project) {
		projects.removeIf(existingProject -> existingProject.getName()
				.contentEquals(project.getName()));
		return projects;
	}

}