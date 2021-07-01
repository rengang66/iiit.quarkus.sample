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
package com.iiit.quarkus.sample.gof23.behavioralpattern.chainofresponsibility;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class ChainofresponsibilityClient implements QuarkusApplication {
	
	@ConfigProperty(name = "gof23.behavioralpattern.chainofresponsibility.title", defaultValue = "gof23")
	String title;
	
	@Inject
	SuperManOne superMan1;
	
	@Inject
	SuperManTwo superMan2;
	
	@Inject
	SuperManThree superMan3;
	
	@Inject
	PrimaryMan primaryMan;
	
	@Override
	public int run(String... args)  {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
				
		//形成一个闭环的链
		superMan1.setSuccessorSuperMan(superMan2);
		superMan2.setSuccessorSuperMan(superMan3);
		superMan3.setSuccessorSuperMan(superMan1);
		
		//设置提问者的问题和答案		
		primaryMan.setThisAnswer("ANSWER");
		
		//让superMan1能回答这个答案
		superMan1.setAnswer("ANSWER1");
		
		//让superMan2能回答这个答案
		superMan2.setAnswer("ANSWER2");
		
		//让superMan3能回答这个答案
		superMan3.setAnswer("ANSWER");
		superMan1.answerTheQuestion(primaryMan);
		return 0;
	}
	
	public static void main(String... args) {
		Quarkus.run(ChainofresponsibilityClient.class, args);
	}

}
