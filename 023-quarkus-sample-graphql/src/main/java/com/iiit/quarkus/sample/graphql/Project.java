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

import java.util.ArrayList;
import java.util.List;


public class Project {

	private Integer id;
	private String name;
	private String description;
	private int level = 1;
	
	private List<Project> childProjects = new ArrayList<>();

	public Project() {
	}
	
	public Project(Integer id, String name) {
		this.id = id;
		this.name = name;		
	}

	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Project(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return this.level;
	}	
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}	

	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setChildProjects(List<Project> childProjects){
		this.childProjects = childProjects;
	}
	
	public List<Project> getChildProjects(){
		return this.childProjects;
	}
	
	public Project addChildProject (Project childProject){
		if (!isExist(childProject)) {
			childProject.level = this.level + 1 ;
			childProjects.add(childProject);
		}
		return this;
	}
	
    public Project deleteChildProject (Project childProject){    	
    	for (int i = 0; i < childProjects.size(); i++) {
            if (childProject.name == ((Project) childProjects.get(i)).name){
            	childProjects.remove(childProject);
            }
         }
		return this;
	}

    public Project updateChildProject (Project childProject){
		if (isExist(childProject)) {
			deleteChildProject (childProject);
			childProject.level = this.level + 1 ;
			addChildProject(childProject);
		}
		return this;
	}
    
    private boolean isExist(Project childProject){    	
    	boolean isExist = false;
    	for (int i = 0; i < childProjects.size(); i++) {
           if (childProject.name == ((Project) childProjects.get(i)).name){
        	   return true;
           }
        }
    	return isExist;
    }    
}
