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
package com.iiit.quarkus.sample.gof23.behavioralpattern.visitor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaxBureau extends Visitor {
	public void VisitCompanyA(CompanyA company) {
		System.out.println("对CompanyA类公司进行税务审计工作");
	}

	public void VisitCompanyB(CompanyB company) {
		System.out.println("对CompanyB类公司进行税务审计工作");
	}

}
