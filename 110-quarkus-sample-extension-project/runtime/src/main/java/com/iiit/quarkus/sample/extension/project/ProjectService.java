package com.iiit.quarkus.sample.extension.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectService {

	private Map<Integer, Project> projectMap = new HashMap<>();

	public ProjectService() {
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

	public List<Project> add(Project project) {
		//projects.add(project);
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

}