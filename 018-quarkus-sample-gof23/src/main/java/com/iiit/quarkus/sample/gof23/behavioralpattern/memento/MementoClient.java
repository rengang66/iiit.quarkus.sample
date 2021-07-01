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
package com.iiit.quarkus.sample.gof23.behavioralpattern.memento;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class MementoClient implements QuarkusApplication {
	
	@ConfigProperty(name = "gof23.behavioralpattern.memento.title", defaultValue = "gof23")
	String title;
	
	@Inject
	Meeting meet1,meet2 ;
	
	@Inject
	Caretaker caretaker;
	
	@Override
	public int run(String... args) {
		
		System.out.println("——————————————————" + title + "演示输出——————————————");	
		
		meet1.setMeetName("meetName1");
		meet1.setMeetType("meetType1");
		meet1.setMeetdate("2009-01-01");
		meet1.setMeetLeader("meetLeader1");
		meet1.setMeetParticipants("meetParticipants1");
		meet1.setDecide("decide1");		
		meet1.doDecide();		
		Memento memento = meet1.CreateMemento();		
		
		//Caretaker caretaker = new Caretaker();
		caretaker.saveMemento(memento.getMeetdate(),memento);
		
		//Meeting meet2 = new Meeting();
		meet2.setMeetName("meetName2");
		meet2.setMeetType("meetType2");
		meet2.setMeetdate("2009-02-01");
		meet2.setMeetLeader("meetLeader2");
		meet2.setMeetParticipants("meetParticipants2");
		meet2.setDecide("decide2");		
		meet2.doDecide();		
		memento = meet2.CreateMemento();
				
		caretaker.saveMemento(memento.getMeetdate(),memento);
		
		caretaker.showContent();	
		
		return 0;
	}
	
	public static void main(String... args) {
		Quarkus.run(MementoClient.class, args);
	}
	
}
