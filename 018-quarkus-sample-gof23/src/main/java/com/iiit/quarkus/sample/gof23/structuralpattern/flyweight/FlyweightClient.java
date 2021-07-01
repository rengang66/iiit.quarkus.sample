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
package com.iiit.quarkus.sample.gof23.structuralpattern.flyweight;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class FlyweightClient implements QuarkusApplication {

	@ConfigProperty(name = "gof23.structuralpattern.flyweight.title", defaultValue = "gof23")
	String title;
	
	@Inject
	DocumentRepository repository ;
	
	@Override
	public int run(String... args) {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
		
		repository.initizeRepository();

		Document doc1 = repository.getDocument("行政文档");
		doc1.readDocument();

		Document doc2 = repository.getDocument("技术文档");
		doc2.readDocument();

		Document doc3 = repository.getDocument("财务文档");
		doc3.readDocument();

		return 0;
	}

	public static void main(String... args) {
		Quarkus.run(FlyweightClient.class, args);
	}

}
