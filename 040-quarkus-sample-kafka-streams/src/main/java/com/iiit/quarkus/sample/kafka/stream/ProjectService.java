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
package com.iiit.quarkus.sample.kafka.stream;

import java.util.Properties;
import java.util.Arrays;
import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jboss.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

@Singleton
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);	
		
	@Inject
	Startup startup;
	
	private boolean is_startup = false;
		
	public void config() {
		if ( !is_startup ){
			startup.Streams();
			is_startup = true;
		}		
    }
	
	public void producer( String content) {
		LOGGER.info("生产数据");		
		Producer<String, String> producer = new KafkaProducer<String, String>(
				getProducerProperties());		
		producer.send(new ProducerRecord<String, String>(Startup.INPUT_TOPIC, content));		
		System.out.println("Message sent successfully");
		producer.close();
	}	
	
	public void commit() {
		LOGGER.info("提交批量数据");				
		Producer<String, String> producer = new KafkaProducer<String, String>(
				getProducerProperties());		
		String tempString = "this is send content;";		
		producer.send(new ProducerRecord<String, String>(Startup.INPUT_TOPIC, tempString));		
		System.out.println("Message sent successfully");
		producer.close();		
	}
	
	public void consumer() {
		LOGGER.info("消费数据");			
		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(getConsumerProperties());		
		kafkaConsumer.subscribe(Arrays.asList(Startup.OUTPUT_TOPIC)); 
		while (true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> record : records) {
				// print the offset,key and value for the consumer records.
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
			}
		}		
	}
	
	private Properties getProducerProperties(){		
		Properties props = new Properties();		
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");		
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}
	
	private Properties getConsumerProperties(){		
		Properties props = new Properties();		
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		//props.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		return props;
	}
	
}
