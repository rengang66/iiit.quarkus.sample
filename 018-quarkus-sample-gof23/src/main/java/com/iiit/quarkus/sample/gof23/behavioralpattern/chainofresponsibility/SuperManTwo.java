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

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SuperManTwo extends AbstractSuperMan{
	private boolean isAnswer = false;
	public void answerTheQuestion(PrimaryMan man){
		if (man.question(answer)) {
			System.out.println("SuperManTwo回答正确。");
		} else {
			if (isAnswer)
				System.out.println("大家都不能回答这个问题。");
			else {
				isAnswer = true;
				if (successorSuperMan != null) {
					System.out.println("SuperManTwo不会回答问题，提交下一个。");
					successorSuperMan.answerTheQuestion(man);
				}
			}
		}
	}

}
