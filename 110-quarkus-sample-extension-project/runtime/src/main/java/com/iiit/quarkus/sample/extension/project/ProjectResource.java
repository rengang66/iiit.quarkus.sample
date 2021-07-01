package com.iiit.quarkus.sample.extension.project;


import java.util.Set;

import com.iiit.quarkus.sample.extension.project.Project;
import com.iiit.quarkus.sample.extension.project.ProjectService;;


public class ProjectResource {	
	
	
	private ProjectService service;

	public ProjectResource(ProjectService service) {
		this.service = service;
	}
	

    
}