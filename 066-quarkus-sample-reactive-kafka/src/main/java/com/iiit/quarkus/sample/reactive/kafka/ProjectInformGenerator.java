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
package com.iiit.quarkus.sample.reactive.kafka;

import java.time.Duration;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ProjectInformGenerator{
	
	private static final Logger LOGGER = Logger.getLogger(ProjectInformGenerator.class);	
		    
    @Outgoing("generated-inform")
    public Multi<String> generate() {
    	int count = 100;
    	String name = "这是项目信息 :"; 
    	
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
        		.onItem().transform(n ->{
        		   String inform = String.format("各位 %s - %d", name, n);
        		   LOGGER.info("生产的数据：" + inform);
        		   return inform;
        		})
        		.transform().byTakingFirstItems(count);        
    }    

}
