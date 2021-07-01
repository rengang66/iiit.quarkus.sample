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
package com.iiit.quarkus.sample.hello;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class HelloService {
	
	@ConfigProperty(name = "hello.message")
    String message;
	
	@ConfigProperty(name = "hello.name", defaultValue = "reng")
	String helloName;
	
	@CacheResult(cacheName = "hello-cache")
	public String getHello() {
		 try {
	            Thread.sleep(2000L);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }		
        return "获取"+message+"的时间：";
    }

    public String getHello(String name) {
        return helloName+":" + name;
    }

}
