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

import javax.enterprise.context.Dependent;

@Dependent
public class Meeting {
	private String meetName;
	private String meetType;
	private String meetdate;
	private String meetLeader;
	private String meetParticipants;
	private String decide;
	private String meetContent;

	public Meeting(){}
	
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

	public void doDecide() {
		meetContent = "会议名称：" + meetName + ";";
		meetContent = meetContent + "会议类型：" + meetType + ";";
		meetContent = meetContent + "会议时间：" + meetdate + ";";
		meetContent = meetContent + "会议主持人：" + meetLeader + ";";
		meetContent = meetContent + "会议参加者：" + meetParticipants + "\r";
		meetContent = meetContent + "会议内容：" + meetParticipants + ";";
	}

	public Memento CreateMemento() {
		Memento mo = new Memento(meetName, meetType, meetdate, meetLeader,
				meetParticipants, decide, meetContent);
		return mo;
	}
	
	public void recoveryMemento(Memento mo){
		meetName = mo.getMeetName();
		meetType = mo.getMeetType();
		meetdate = mo.getMeetdate();
		meetLeader = mo.getMeetLeader();
		meetParticipants = mo.getMeetParticipants();
		decide = mo.getDecide();
		meetContent = mo.getMeetContent();
	}
	
	public void showContent(){
		System.out.println(meetContent);
	}

}
