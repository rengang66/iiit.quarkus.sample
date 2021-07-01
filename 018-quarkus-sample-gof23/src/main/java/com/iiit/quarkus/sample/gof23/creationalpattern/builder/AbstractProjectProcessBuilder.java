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

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractProjectProcessBuilder {	
	List<String> processList = new ArrayList<String>();
	//可行性分析过程
	public void buildFeasibility(){};
	//技术交流过程
	public void buildTechnicalDiscussion(){};
	//投标过程
	public void buildBid(){};
	//需求调研和分析过程
	public void buildRequirement(){};
	//设计过程
	public void buildDesign(){};
	//编码过程
	public void buildProgram(){};
	//测试过程
	public void buildTest(){};
	//部署和实施过程
	public void buildDeployment(){};
	//维护过程
	public void buildMaintenance(){};
	
	public void showProcess(){
		for (int i=0; i<processList.size(); i++){
			System.out.print(processList.get(i) + "  ");
			System.out.println();
		}		
	}

}
