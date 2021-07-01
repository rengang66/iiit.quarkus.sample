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
package com.iiit.quarkus.sample.gof23.structuralpattern.bridge;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class BridgeClient implements QuarkusApplication {

	@ConfigProperty(name = "gof23.structuralpattern.bridge.title", defaultValue = "gof23")
	String title;
	
	@Inject
	Training action1;

	@Inject
	DevelopmentDep depart1;
	
	@Inject
	Meeting action2;
	
	@Inject
	FinanceDep depart2;
	
	@Inject
	Training action3;
	
	@Inject
	MarketDep depart3;
	
	public void scene1() {
		// 场景1：针对开发部的培训工作
		//AbstractAction action = new Training();
		//AbstractDepartment depart = new DevelopmentDep();
		depart1.setAbstractAction(action1);
		depart1.action("提高开发技能");
	}

	public  void scene2() {
		// 场景2：针对财务部的会议
		//AbstractAction action = new Meeting();
		//AbstractDepartment depart = new FinanceDep();
		depart2.setAbstractAction(action2);
		depart2.action("检查会计制度");
	}

	public  void scene3() {
		// 场景2：针对市场部的培训
		//AbstractAction action = new Training();
		//AbstractDepartment depart = new MarketDep();
		depart3.setAbstractAction(action3);
		depart3.action("沟通技巧");
	}

	@Override
	public int run(String... args) {		
		scene1();
		scene2();
		scene3();
		return 0;
	}
		

	public static void main(String... args) {
		Quarkus.run(BridgeClient.class, args);
	}

}
