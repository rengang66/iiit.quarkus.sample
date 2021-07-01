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
package com.iiit.quarkus.sample.jms.artemis;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.jms.Topic;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ProjectInformProducer implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(ProjectInformProducer.class);

	//@Inject
	//TopicConnectionFactory connectionFactory;
	
	@Inject
	ConnectionFactory connectionFactory;
	

	private final Random random = new Random();
	private final ScheduledExecutorService scheduler = Executors
			.newSingleThreadScheduledExecutor();

	void onStart(@Observes StartupEvent ev) {
		LOGGER.info("ScheduledExecutorService启动");
		scheduler.scheduleWithFixedDelay(this, 0L, 5L, TimeUnit.SECONDS);
	}

	void onStop(@Observes ShutdownEvent ev) {
		LOGGER.info("ScheduledExecutorService关闭");
		scheduler.shutdown();
	}

	@Override
	public void run()  {
		// LOGGER.info("给主题发送消息");
		try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {						
			
			Connection connection=connectionFactory.createConnection(); // 通过连接工厂获取连接
            connection.start(); // 启动连接
            Session session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE); // 创建Session
                      
            Topic topic = session.createTopic("ProjectInform");
            MessageProducer messageProducer= session.createProducer(topic); // 创建消息生产者        
                        
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(new Date());
			String sendContent = "项目进程数据: " +  Integer.toString(random.nextInt(100));			
			System.out.println( dateString + " JMSProducer通过主题ProjectInform发布数据: " + sendContent);
			TextMessage message=session.createTextMessage(sendContent);           
            messageProducer.send(message);
            session.commit();			
		} catch( JMSException e){
	         System.out.println("Exception thrown  :" + e);	     
		}		
		
	}

}
