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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.BasicDBObject;

@ApplicationScoped
public class ProjectService {

	@Inject
	MongoClient mongoClient;

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
	public List<Project> list() {
		List<Project> list = new ArrayList<>();
		MongoCursor<Document> cursor = getCollection().find().iterator();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();				
				Project project = new Project(document.getString("name"),
						document.getString("description"));
				list.add(project);
			}
		} finally {
			cursor.close();
		}
		return list;
	}
	
	// 从MongoDB中获取projects数据库iiit_projects集合中的所有数据并存入到List
	public List<Project> get(String name) {
			List<Project> list = new ArrayList<>();
			MongoCursor<Document> cursor = getCollection().find().iterator();
			try {
				while (cursor.hasNext()) {
					Document document = cursor.next();					
					if ( document.getString("name").equals(name) ){
						Project project = new Project(document.getString("name"),
								document.getString("description"));
						list.add(project);
					}					
				}
			} finally {
				cursor.close();
			}
			return list;
	}

	// 在MongoDB的projects数据库iiit_projects集合中新增一条Document
	public void add(Project project) {
		Document document = new Document().append("name", project.name).append(
				"description", project.description);
		getCollection().insertOne(document);
	}

	// 在MongoDB的projects数据库iiit_projects集合中修改一条Document
	public void update(Project project) {
		Document document = new Document().append("name", project.name).append(
				"description", project.description);
		getCollection().deleteOne(Filters.eq("name", project.name));
		add(project);
	}

	// 在MongoDB的projects数据库iiit_projects集合中删除一条Document
	public void delete(Project project) {
		getCollection().deleteOne(Filters.eq("name", project.name));
	}

	// 删除MongoDB的projects数据库iiit_projects集合中的所有记录
	private void deleteAll() {
		BasicDBObject document = new BasicDBObject();
		getCollection().deleteMany(document);
	}

	// 获取MongoDB的projects数据库iiit_projects集合对象
	private MongoCollection getCollection() {
		return mongoClient.getDatabase("projects").getCollection(
				"iiit_projects");
	}

}