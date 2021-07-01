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
package com.iiit.quarkus.sample.microprofile.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ProjectBusinessHealthCheck implements HealthCheck {

    @ConfigProperty(name = "business.up", defaultValue = "false")
    boolean businessUP;

    @Override
    public HealthCheckResponse call() {
    	
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Project业务逻辑健康检测");

        try {
        	BusinessVerification();
            responseBuilder.up();
        } catch (IllegalStateException e) {      
            responseBuilder.down().withData("error", e.getMessage()); 
        }
        return responseBuilder.build();
    }

    private void BusinessVerification() {
        if (!businessUP) {
            throw new IllegalStateException("警告，Project业务逻辑有问题！！");
        }
    }
}
