package com.iiit.quarkus.sample.kafka.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;


public class Application {	
	
    public static void main(String[] args) {
    
        Properties prop = new Properties();
        prop.put(StreamsConfig.APPLICATION_ID_CONFIG,"WordCount");
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        prop.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,3000);        
        prop.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        prop.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        
        //构建流构造器
        StreamsBuilder builder = new StreamsBuilder();
        KTable<String, Long> count = builder.stream("wordcount-input") //从kafka中一条一条的取数据
                .flatMapValues( //返回压扁的数据
                        (value) -> {  //对数据进行按空格切割，返回list集合
                            String[] split = value.toString().split(" ");
                            List<String> strings = Arrays.asList(split);
                            return strings;
                        }).map((k, v) -> {
                    return new KeyValue<String, String>(v, "1");
                }).groupByKey().count();
		
		//在控制台输出结果
        count.toStream().foreach((k,v)->{ System.out.println("key:"+k+"  value:"+v); });

        count.toStream().map((x,y)->{
            return new KeyValue<String,String>(x,y.toString());
        }).to("wordcount-out");

        Topology topo = builder.build();
        KafkaStreams stream = new KafkaStreams(topo, prop);
        final CountDownLatch latch=new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("stream"){
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

