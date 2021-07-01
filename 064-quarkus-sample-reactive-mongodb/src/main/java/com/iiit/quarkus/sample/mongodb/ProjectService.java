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
package com.iiit.quarkus.sample.mongodb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;

import com.mongodb.client.model.Filters;
import com.mongodb.BasicDBObject;

@ApplicationScoped
public class ProjectService {

	@Inject
    ReactiveMongoClient mongoClient;

	@Inject
	@ConfigProperty(name = "iiit_projects.init.insert", defaultValue = "true")
	boolean initInsertData;

	public ProjectService() {
	}

	@PostConstruct
	void config() {
		if (initInsertData) {
			initDBdata();
		}
	}

	// 初始化数据
	private void initDBdata() {
		deleteAll();
		Project project1 = new Project("项目A", "关于项目A的描述");
		Project project2 = new Project("项目B", "关于项目B的描述");
		add(project1);
		add(project2);
	}

	// 从MongoDB中获取projects数据库iiit_projects集合中的所有数据并存入到List
	public Uni<List<Project>> list() {		
		 return getCollection().find()
	                .map(doc -> {
	                	Project project = new Project(doc.getString("name"),
	                			doc.getString("description"));	                	
	                    return project;
	                }).collectItems().asList();		
	}
		
	
	public Uni<List<Project>> get( String name) {
		 return getCollection().find()
	                .map(doc -> {
	                	if (doc.getString("name").equals(name))
	                	{
	                		Project project = new Project(doc.getString("name"),
		                			doc.getString("description"));              	
		                    return project; 
	                	}
	                	return null;
	                }).collectItems().asList();
	}
	
	

	// 在MongoDB的projects数据库iiit_projects集合中新增一条Document
	public Uni<Void> add(Project project) {
		Document document = new Document().append("name", project.name).append(
				"description", project.description);		
		return   getCollection().insertOne(document).onItem().ignore().andContinueWithNull();		
	}

	// 在MongoDB的projects数据库iiit_projects集合中修改一条Document
	public Uni<Void> update(Project project) {		
		getCollection().deleteOne(Filters.eq("name", project.name));
		return add(project);		
	}

	// 在MongoDB的projects数据库iiit_projects集合中删除一条Document
	public Uni<Void> delete(Project project) {			
		return getCollection().deleteOne(Filters.eq("name", project.name))
                .onItem().ignore().andContinueWithNull();		 
	}
	
	// 删除MongoDB的projects数据库iiit_projects集合中的所有记录
	private void deleteAll() {
		BasicDBObject document = new BasicDBObject();
		getCollection().deleteMany(document);
	}	

	// 获取MongoDB的projects数据库iiit_projects集合对象
	private ReactiveMongoCollection<Document> getCollection() {
		return mongoClient.getDatabase("projects").getCollection(
				"iiit_projects");
	}
}