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
package com.iiit.quarkus.sample.gof23.structuralpattern.flyweight;

import java.util.Map;
import java.util.HashMap;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentRepository {
	
	private AdministrativeDcoment administrativeDcoment;
	
	private TechnicalDocument technicalDocument;
	
	private FinancialDocument financialDocument;
	
	private Map<String,Document> DocumentMap = new HashMap<String,Document>();	
	
	public DocumentRepository (AdministrativeDcoment administrativeDcoment,
			TechnicalDocument technicalDocument,FinancialDocument financialDocument){			
		this.administrativeDcoment = administrativeDcoment;
		this.technicalDocument = technicalDocument;
		this.financialDocument = financialDocument;		
	}
	
	public void initizeRepository (){
		DocumentMap.put("行政文档", this.administrativeDcoment);
		DocumentMap.put("技术文档", this.technicalDocument);
		DocumentMap.put("财务文档", this.financialDocument);		
	}	
	
	public Document getDocument(String docType){		
		if (DocumentMap.containsKey(docType)){
			return DocumentMap.get(docType);
		}
		System.out.print("没有此类文档。");
		return null;
	}

}
