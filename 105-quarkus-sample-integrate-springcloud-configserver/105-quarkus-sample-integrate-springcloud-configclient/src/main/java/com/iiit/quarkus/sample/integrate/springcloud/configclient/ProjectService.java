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
package com.iiit.quarkus.sample.integrate.springcloud.configclient;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;


@ApplicationScoped
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class.getName());
		
	
	@Inject
    @ConfigProperty(name = "init.data.create", defaultValue = "true")
    boolean isInitData;	

	@Inject
	Project1Properties properties1;
	
	@Inject
	Project2Properties properties2;
	
	private Set<Project> projects = Collections.newSetFromMap(Collections
			.synchronizedMap(new LinkedHashMap<>()));

	public ProjectService() {			
	}
	
	//初始化数据
	@PostConstruct
    void initData() {
		LOGGER.info("初始化数据");
        if (isInitData) {
        	Project project1 = new Project(properties1.id, properties1.inform.name, properties1.inform.description);
    		Project project2 = new Project(properties2.id, properties2.inform.name, properties2.inform.description);
    		    		
    		projects.add(project1);
    		projects.add(project2);
        }
    }	

	public Set<Project> list() {
		return projects;
	}

	public Project getById(Integer id) {		
		for (Project value : projects) {			
			if ( (id.intValue()) == (value.id.intValue())) {				
				return value;
			}
		}
		return null;
	}

}