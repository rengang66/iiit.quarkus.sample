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

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.MessageListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

@ApplicationScoped
public class Listener implements MessageListener {
	
	private static final Logger LOGGER = Logger.getLogger(Listener.class);	

	String consumeContent ;
	
	@Override
    public void onMessage(Message message) {
        try {        	
        	consumeContent = ((TextMessage) message).getText();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                
            String dateString = formatter.format(new Date());     
            System.out.println( dateString+ " Listener通过主题ProjectInform订阅数据: " + consumeContent );
            LOGGER.info("Listener成功获取数据，内容为："+consumeContent);             
        } catch (JMSException e) {            
            e.printStackTrace();
        }
    }
}
