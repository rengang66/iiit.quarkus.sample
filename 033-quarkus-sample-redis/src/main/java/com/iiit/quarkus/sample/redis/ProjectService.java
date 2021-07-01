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
package com.iiit.quarkus.sample.redis;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jboss.logging.Logger;

import io.quarkus.redis.client.RedisClient;

@Singleton
class ProjectService {
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	//注入Redis客户端
    @Inject
    RedisClient redisClient;   
    
    ProjectService() {    	
    }
    
    //在Redis中初始化数据
    @PostConstruct
    void config() {
    	LOGGER.info("在Redis中初始化数据");
    	add("project1", "关于project1的情况描述");
    	add("project2", "关于project2的情况描述");
    }    

    //在Redis中获得某主键的值
    public String get(String key) {
        return redisClient.get(key).toString();
    }

    //在Redis中给某主键赋值
    public void add(String key,String value) {
    	if ( get(key) != null ) return ;
        redisClient.set(Arrays.asList(key.toString(), value));      
    }

    //在Redis中给某主键修改内容
    public void update(String key, String value) {
        redisClient.getset(key,value);
    }
    
  //在Redis中删除某主键的值
    public void del(String key) {
    	LOGGER.info("在Redis中删除数据"+key);
    	redisClient.del(Arrays.asList(key));        
    }
    
}

