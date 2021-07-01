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
package com.iiit.quarkus.sample.openapi.swaggerui;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectService {

	private Set<Project> projects = Collections.newSetFromMap(Collections
			.synchronizedMap(new LinkedHashMap<>()));

	public ProjectService() {
		projects.add(new Project("项目A", "关于项目A的情况描述"));
		projects.add(new Project("项目B", "关于项目B的情况描述"));
	}

	public Set<Project> list() {
		return projects;
	}

	public Set<Project> add(Project project) {
		projects.add(project);
		return projects;
	}

	public Set<Project> update(Project project) {
		projects.removeIf(existingProject -> existingProject.name
				.contentEquals(project.name));
		projects.add(project);
		return projects;
	}

	public Set<Project> delete(Project project) {
		projects.removeIf(existingProject -> existingProject.name
				.contentEquals(project.name));
		return projects;
	}

}