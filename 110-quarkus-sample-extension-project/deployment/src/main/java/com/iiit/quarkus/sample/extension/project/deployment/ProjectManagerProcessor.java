package com.iiit.quarkus.sample.extension.project.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;

import com.iiit.quarkus.sample.extension.project.Project;
import com.iiit.quarkus.sample.extension.project.ProjectService;
import com.iiit.quarkus.sample.extension.project.ProjectResource;

class ProjectManagerProcessor {

	private static final String FEATURE = "quarkus-sample-extension-project";

	@BuildStep
	FeatureBuildItem feature() {
		return new FeatureBuildItem(FEATURE);
	}

	@BuildStep
	public AdditionalBeanBuildItem buildProject() {
		return new AdditionalBeanBuildItem(Project.class);
	}
	
	@BuildStep
    void load(BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        additionalBeans.produce(new AdditionalBeanBuildItem(ProjectService.class));
        additionalBeans.produce(new AdditionalBeanBuildItem(ProjectResource.class));
    }



}
