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
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@Liveness
@Readiness
@ApplicationScoped
public class ProjectDataHealthCheck implements HealthCheck {

	@Inject
	ProjectService service;
	
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("数据访问健康检测")
                .up()
                .withData(((Project)service.getProjectById(1)).name, ((Project)service.getProjectById(1)).description)
                .withData(((Project)service.getProjectById(2)).name, ((Project)service.getProjectById(1)).description)
                .build();
    }
}
