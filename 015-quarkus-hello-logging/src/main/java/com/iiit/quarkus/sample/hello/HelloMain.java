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

import javax.inject.Inject;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

@QuarkusMain
public class HelloMain implements QuarkusApplication {   
    
    @Inject
    HelloResource service;
        

    @Override
    public int run(String... args) {    	
    	if(args.length>0) {
        	System.out.println("hi,commond mode,this is args:" + args);
        } else {
        	System.out.println("hi,commond mode");
        }         
        
        Quarkus.waitForExit();
        return 0;
    }    
    
    
    public static void main(String... args) {
        Quarkus.run(HelloMain.class, args);
    }   


}
