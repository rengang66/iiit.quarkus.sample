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
package com.iiit.quarkus.sample.reactive.redis;

import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.logging.Logger;

@Singleton
class ProjectService {
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);	

	//注入ReactiveRedisClient客户端
    @Inject
    ReactiveRedisClient reactiveRedisClient;
    
    ProjectService() {    	
    }   
    
    public Uni<List<String>> keys() {
        return reactiveRedisClient
                .keys("*")
                .map(response -> {
                    List<String> result = new ArrayList<>();
                    for (Response r : response) {
                        result.add(r.toString());
                    }
                    return result;
                });
    }
    
  //在Redis中给某主键赋值
    public Uni<Response> add(String key,String value) {
    	LOGGER.info("在Redis中增加某主键赋值");     	
    	return reactiveRedisClient.getset(key,value);
    }

  //在Redis中获得某主键的值
    public Uni<Project> get(String key) {
    	Uni<Project> result = reactiveRedisClient.get(key).map(response ->{
    		String value = response.toString();
    		Project project = new Project(key,value);
    				return project;});     	
        return result;
    }    

  //在Redis中给某主键修改内容
    public Uni<Response> update(String key, String value) {
    	LOGGER.info("在Redis中给某主键赋值");    	
    	return reactiveRedisClient.getset(key,value);
    }

    //在Redis中删除某主键的值
    public Uni<Void> del(String key) {
        return reactiveRedisClient.del(Arrays.asList(key))
                .map(response -> null);
    }
}

