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
package com.iiit.quarkus.sample.gof23.behavioralpattern.mediator;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TechnicalDirector extends Mediator {

	private ProjectA projectA;
	private ProjectB projectB;
	private String directorName;

	public TechnicalDirector(){}
	
	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public ProjectA getProjectA() {
		return projectA;
	}

	public void setProjectA(ProjectA projectA) {
		this.projectA = projectA;
	}

	public ProjectB getProjectB() {
		return projectB;
	}

	public void setProjectB(ProjectB projectB) {
		this.projectB = projectB;
	}

	public void doCoordination() {
		

	}

}
