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
package com.iiit.quarkus.sample.gof23.creationalpattern.abstractfactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyA implements Company{	
	
	public Computer bulidComputer(String Parameter){
		if (Parameter.equals("个人电脑")) return new PersonalComputer();
		else if  (Parameter.equals("笔记本电脑")) return new NotebookComputer();
		else return null;
	}
	
	public Telephone bulidTelephone(String Parameter){
		if (Parameter.equals("座机电话")) return new DesktopPhone();
		else if  (Parameter.equals("手机")) return new Mobile();
		else return null;
	}
	
}
