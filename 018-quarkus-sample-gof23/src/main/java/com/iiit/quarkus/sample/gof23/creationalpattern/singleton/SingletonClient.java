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
package com.iiit.quarkus.sample.gof23.creationalpattern.singleton;


import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class SingletonClient implements QuarkusApplication {

	@ConfigProperty(name = "gof23.creationalpattern.singleton.title", defaultValue = "gof23")
	String title;
	
	@Inject
	SaleMan saleMan ;
	
	@Inject
	ServiceManager service ;		
	
	@Override
	public int run(String... args) {

		//SaleMan saleMan = new SaleMan("小刘", "小刘的服务");
		
		saleMan.setName("小刘");
		saleMan.setService("小刘的服务");
		
		//ServiceManager service = new ServiceManager(saleMan);

		System.out.print("第一次获得服务：");
		SaleMan saleman = service.getSaleManService();
		System.out.println(saleman.getService());

		System.out.print("第二次获得服务：");
		saleman = service.getSaleManService();
		System.out.println(saleman.getService());

		System.out.print("第三次获得服务：");
		saleman = service.getSaleManService();
		System.out.println(saleman.getService());

		return 0;
	}

	public static void main(String... args) {
		Quarkus.run(SingletonClient.class, args);
	}

}
