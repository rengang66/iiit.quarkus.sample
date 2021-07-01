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
package com.iiit.quarkus.sample.gof23.structuralpattern.composite;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class CompositeClient implements QuarkusApplication {

	@ConfigProperty(name = "gof23.structuralpattern.composite.title", defaultValue = "gof23")
	String title;
	
	@Inject
	Corporation corporation1;

	@Inject
	Department depart1, depart2, depart3;

	@Inject
	WorkTeam team1, team2;

	public void scene1() {
		// 场景1，形成公司-部门-项目组的公司结构
		// AbstractOrganization corporation = new Corporation("公司A");
		corporation1.addName("公司A");

		// AbstractOrganization depart1= new Department("部门1");
		// AbstractOrganization depart2= new Department("部门2");
		// AbstractOrganization depart3= new Department("部门3");
		depart1.addName("部门1");
		depart2.addName("部门2");
		depart3.addName("部门3");

		corporation1.add(depart1);
		corporation1.add(depart2);
		corporation1.add(depart3);		

		team1.addName("项目组1");
		team2.addName("项目组2");

		depart1.add(team1);
		depart1.add(team2);

		corporation1.showOrganizationStructure("");
	}

	@Inject
	Corporation corporation2;

	@Inject
	Factory factory1, factory2, factory3;

	@Inject
	Workshop team3, team4;

	public void scene2() {
		// 场景2，形成公司-工厂-车间的工厂结构
		
		corporation2.addName("公司B");		

		factory1.addName("工厂1");
		factory2.addName("工厂2");
		factory3.addName("工厂3");

		corporation2.add(factory1);
		corporation2.add(factory2);
		corporation2.add(factory3);
		
		team3.addName("车间1");
		team4.addName("车间2");

		factory1.add(team3);
		factory1.add(team4);

		corporation2.showOrganizationStructure("");
	}

	@Override
	public int run(String... args) {
		System.out.println("——————————————————" + title + "演示输出——————————————");	
		
		scene1();
		System.out.println();
		scene2();
		return 0;
	}

	public static void main(String... args) {
		Quarkus.run(CompositeClient.class, args);
	}

}
