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
package com.iiit.quarkus.sample.gof23.structuralpattern.adapter;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class AdapterClient implements QuarkusApplication {
	
	@ConfigProperty(name = "gof23.structuralpattern.adapter.title", defaultValue = "gof23")
	String title;
	
	@Inject
	Customer customer;
	
	@Inject
	Designer designer ;
	
	//@Inject
	//Analyst analyst;
	
	@Override
    public int run(String... args)  {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");		
	
		customer.setCustomerName("客户小王");
		customer.setRequirement("客户小王的需求");
	
		designer.setName("开发员小张");
		
		Analyst analyst = new Analyst("分析员小刘");	
		
		//客户把用户需求提交给分析员
		analyst.setRequest(customer.commitRequirement());
		//分析员经过转化后提供给开发人员
		analyst.setDesigner(designer);
		//得到满足客户的需求的工作产品
		System.out.println(analyst.getFinishworks());
		
		return 0;

	}
	
	public static void main(String... args) {		
		Quarkus.run(AdapterClient.class, args);		
	}
	
	

}
