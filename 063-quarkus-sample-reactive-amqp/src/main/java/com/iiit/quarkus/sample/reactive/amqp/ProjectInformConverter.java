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
package com.iiit.quarkus.sample.reactive.amqp;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ProjectInformConverter {

	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String dateString = null;
	
	public ProjectInformConverter() {
	}

	@Incoming("inform")
	@Outgoing("data-stream")
	@Broadcast
	@Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
	public String process(String inform) {		
		dateString=formatter.format(new Date());
		System.out.println(dateString + " ProjectInformConverter接收并转发的数据: " + inform);
		LOGGER.info("接收并转发的数据：" + inform);
		return inform;
	}

}