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

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KTable;

import io.quarkus.runtime.StartupEvent;

@Singleton
public class Startup {
	
	public static final String INPUT_TOPIC = "wordcount-input";
    public static final String OUTPUT_TOPIC = "wordcount-out";
	
	@Inject
    KafkaStreams stream;
  
	 public void Streams() {  
   // public void Streams(@Observes StartupEvent evt) {    	
    	Properties prop = new Properties();
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG,"streams-wordcount");
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        prop.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,3000);        
        prop.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        prop.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        
        //构建流构造器
        StreamsBuilder builder = new StreamsBuilder();
        KTable<String, Long> count = builder.stream(INPUT_TOPIC) //从kafka中一条一条的取数据
                .flatMapValues( //返回处理的数据
                        (value) -> {  //对数据进行按空格切割，返回list集合
                            String[] split = value.toString().split(" ");
                            List<String> strings = Arrays.asList(split);
                            return strings;
                        }).map((k, v) -> {
                    return new KeyValue<String, String>(v, String.valueOf(v.length()));
                 }).groupByKey().count();                  
		
		//在控制台输出结果
        count.toStream().foreach((k,v)->{ System.out.println("key:"+k+" count:"+v +"  length:" + k.toString().length()); });
        
        //数据流从"wordcount-input"主题到"wordcount-out"主题
        count.toStream().map((x,y)->{
            return new KeyValue<String,String>(x,y.toString());
        }).to(OUTPUT_TOPIC);
   
        
        stream = new KafkaStreams(builder.build(), prop);
        final CountDownLatch latch=new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook"){
            @Override
            public void run() {
                stream.close();
                latch.countDown();
            }
        });
        try {
            //启动stream
            stream.start();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    } 
}
