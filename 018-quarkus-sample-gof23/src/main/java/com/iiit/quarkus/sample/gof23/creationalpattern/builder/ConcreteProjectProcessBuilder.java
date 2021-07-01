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
package com.iiit.quarkus.sample.gof23.creationalpattern.builder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConcreteProjectProcessBuilder extends
		AbstractProjectProcessBuilder {

	// 可行性分析过程
	public void buildFeasibility() {
		processList.add("可行性分析过程");
	}

	// 技术交流过程
	public void buildTechnicalDiscussion() {
		processList.add("技术交流过程");
	}

	// 投标过程
	public void buildBid() {
		processList.add("投标过程");
	}

	// 需求调研和分析过程
	public void buildRequirement() {
		processList.add("需求调研和分析过程");
	}

	// 设计过程
	public void buildDesign() {
		processList.add("设计过程");
	}

	// 编码过程
	public void buildProgram() {
		processList.add("编码过程");
	}

	// 测试过程
	public void buildTest() {
		processList.add("测试过程");
	}

	// 部署和实施过程
	public void buildDeployment() {
		processList.add("部署和实施过程");
	}

	// 维护过程
	public void buildMaintenance() {
		processList.add("维护过程");
	}
}
