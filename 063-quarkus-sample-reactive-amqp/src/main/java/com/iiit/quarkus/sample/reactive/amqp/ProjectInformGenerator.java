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
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.reactivex.Flowable;

@ApplicationScoped
public class ProjectInformGenerator {

	private static final Logger LOGGER = Logger.getLogger(ProjectInformGenerator.class);

	private Random random = new Random();

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String dateString, sendContent = null;

	@Outgoing("generated-inform")
	public Multi<String> generateInform() {

		return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
				.onOverflow().drop().map(tick -> {
					dateString = formatter.format(new Date());
					sendContent = "项目进程数据: "
							+ Integer.toString(random.nextInt(100));
					System.out.println(dateString + " ProjectInformGenerator发送数据: " + sendContent);
					return sendContent;
				});
	}

}
