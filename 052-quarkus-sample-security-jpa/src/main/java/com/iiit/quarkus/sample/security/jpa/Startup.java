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
package com.iiit.quarkus.sample.security.jpa;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;

@Singleton
public class Startup {
	
	//注入持久类
	@Inject
	EntityManager entityManager;
	
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
    	
    	// 增加admin用户
    	User admin = new User(1,"admin", "1234", "admin");
    	User entity = entityManager.find(User.class,admin.getId());
    	if ( entity != null) {
    		entityManager.remove(entity);    		
    	}    	
    	entityManager.persist(admin);
    	
    	// 增加user用户
    	User user = new User(2,"user", "user", "user");
    	entity = entityManager.find(User.class,user.getId());
     	if ( entity != null) {
     		entityManager.remove(entity);    		
     	}     	
     	entityManager.persist(user);
     	
     // 增加reng用户
     	User reng = new User(3,"reng", "1234", "user");
     	entity = entityManager.find(User.class,reng.getId());
     	if ( entity != null) {
     		entityManager.remove(entity);    		
     	}     	
     	entityManager.persist(reng);
    }
}
