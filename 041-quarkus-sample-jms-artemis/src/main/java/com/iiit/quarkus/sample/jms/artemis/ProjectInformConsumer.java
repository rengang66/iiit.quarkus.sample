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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.Destination;


import org.jboss.logging.Logger;

@ApplicationScoped
public class ProjectInformConsumer implements Runnable {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectResource.class);	

	public ProjectInformConsumer() {
	}
	
	@Inject
	//TopicConnectionFactory connectionFactory;
	ConnectionFactory connectionFactory;
	
	@Inject
	//MessageListener listener;
	Listener listener;

    private final ExecutorService scheduler = Executors.newSingleThreadExecutor();

    private volatile String consumeContent;

    public String getConsumeContent() {
        return consumeContent;
    }

    void onStart(@Observes StartupEvent ev) {
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) { 
        	LOGGER.info("通过监听订阅消息");        	
        	Connection connection= connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            Session session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 创建连接的消息主题            
            Topic topic = session.createTopic("ProjectInform");
            // 创建消息消费者
            MessageConsumer messageConsumer=session.createConsumer(topic);
            // 注册消息监听
            //messageConsumer.setMessageListener(listener);
            
            while (true) {            	 
            	TextMessage message = (TextMessage) messageConsumer.receive();            	 
            	if (message == null) { 
            		return;          	 
            	}
            	consumeContent = message.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                
                String dateString = formatter.format(new Date());     
                System.out.println( dateString+ " JMSConsumer通过主题ProjectInform订阅数据: " + consumeContent );
                LOGGER.info("消费者成功获取数据，内容为："+consumeContent);           	
            }             
            
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }        
    }

}