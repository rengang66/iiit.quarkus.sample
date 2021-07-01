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
package com.iiit.quarkus.sample.integrate.spring.di;

import org.springframework.stereotype.Component;

@Component("projectStateFunction")
public class ProjectStateFunction implements ProjectFunction {
	
	//实现一个开关功能，当输入为true，返回值是false；当输入为false，返回值是true
    @Override
    public String apply(String isTrue) {    	
    	if (Boolean.valueOf(isTrue)) return "false";
    	return "true";       
    }    
}
