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

public class Memento {
	
	private String meetName;
	private String meetType;
	private String meetdate;
	private String  meetLeader;
	private String  meetParticipants;
	private String decide;
	private String  meetContent;
	
	public Memento(String meetName, String meetType, String meetdate,
			String meetLeader, String meetParticipants, String decide,
			String meetContent) {
		super();
		this.meetName = meetName;
		this.meetType = meetType;
		this.meetdate = meetdate;
		this.meetLeader = meetLeader;
		this.meetParticipants = meetParticipants;
		this.decide = decide;
		this.meetContent = meetContent;
	}
	
	public void showContent(){
		System.out.println(meetContent);
	}

	public String getMeetName() {
		return meetName;
	}

	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}

	public String getMeetType() {
		return meetType;
	}

	public void setMeetType(String meetType) {
		this.meetType = meetType;
	}

	public String getMeetdate() {
		return meetdate;
	}

	public void setMeetdate(String meetdate) {
		this.meetdate = meetdate;
	}

	public String getMeetLeader() {
		return meetLeader;
	}

	public void setMeetLeader(String meetLeader) {
		this.meetLeader = meetLeader;
	}

	public String getMeetParticipants() {
		return meetParticipants;
	}

	public void setMeetParticipants(String meetParticipants) {
		this.meetParticipants = meetParticipants;
	}

	public String getDecide() {
		return decide;
	}

	public void setDecide(String decide) {
		this.decide = decide;
	}

	public String getMeetContent() {
		return meetContent;
	}

	public void setMeetContent(String meetContent) {
		this.meetContent = meetContent;
	}
	
	

}
