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


import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;


@ApplicationScoped
@GraphQLApi
public class ProjectResource {

	// 注入ProjectService对象
	@Inject
	ProjectService service;

	public ProjectResource() {
	}
	
	@Query("projects")
	public Set<Project> list() {
		return service.list();
	}
	
	@Query("project")
	public Project getById(@Name("id") Integer id) {		
		return service.getById(id);
	}

	@Mutation
	public Set<Project> add(Project project) {
		return service.add(project);
	}

	@Mutation
	public Set<Project> update(Project project) {
		return service.update(project);
	}

	@Mutation
	public Set<Project> delete(Project project) {
		return service.delete(project);
	}

}