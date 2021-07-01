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
package com.iiit.quarkus.sample.gof23.creationalpattern.prototype;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyBaseIntroduction extends AbstractPrototype{
private Map<String, String> introductionMap = new HashMap<String, String>();
	
	public CompanyBaseIntroduction(){		
	}
	
	public CompanyBaseIntroduction(Map<String, String> map){
		introductionMap = map;
	}
	
	public AbstractPrototype cloneMyself(){
		addSomeIntrouction("公司介绍", "这是公司基本介绍");	
		AbstractPrototype object = new CompanyBaseIntroduction(introductionMap);		
		return object;
	}	
	
	public void addSomeIntrouction(String topic, String content) {
		introductionMap.put(topic, content);		
	}

	public void deleteSomeIntrouction(String key) {
		if (introductionMap.containsKey(key))
			introductionMap.remove(key);
	}
	
	public void addIntrouctionMap(Map<String, String> map){
		introductionMap.putAll(map);
	}
	
	public Map<String, String> getIntroductionMap(){
		return introductionMap;
	}

	public void showIntrouction() {
		Iterator<String> it = introductionMap.keySet().iterator();
		String key, value;
		while (it.hasNext()) {
			key = (String) it.next();
			value = (String) introductionMap.get(key);
			System.out.println("key: " + key + "; value: " + value);
		}
	}	

}
