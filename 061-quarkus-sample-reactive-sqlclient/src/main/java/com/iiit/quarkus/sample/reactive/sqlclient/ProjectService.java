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
package com.iiit.quarkus.sample.reactive.sqlclient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

@ApplicationScoped
public class ProjectService {
	
	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);	
	
	@Inject
    @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
    boolean schemaCreate;	
	
	@Inject
	PgPool client;	
	
	@PostConstruct
    void config() {
        if (schemaCreate) {
            initdb();
        }
    }

	//初始化数据
    private void initdb() {
    	LOGGER.info("初始化数据");
        client.query("DROP TABLE IF EXISTS iiit_projects").execute()
                .flatMap(r -> client.query("CREATE TABLE iiit_projects (id SERIAL PRIMARY KEY, name TEXT NOT NULL)").execute())
                .flatMap(r -> client.query("INSERT INTO iiit_projects (name) VALUES ('项目A')").execute())
                .flatMap(r -> client.query("INSERT INTO iiit_projects (name) VALUES ('项目B')").execute())
                .flatMap(r -> client.query("INSERT INTO iiit_projects (name) VALUES ('项目C')").execute())
                .flatMap(r -> client.query("INSERT INTO iiit_projects (name) VALUES ('项目D')").execute())
                .await().indefinitely();
    }    
    
    //从数据库获取所有行，针对每行数据组装成一个Project对象，然后放入到List中
    public  Multi<Project> findAll() {
        return client.query("SELECT id, name FROM iiit_projects ORDER BY name ASC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(ProjectService::from);
    }

    //从数据库过滤出指定所有行，组装成一个Project对象
    public Uni<Project> findById(Long id) {
        return client.preparedQuery("SELECT id, name FROM iiit_projects WHERE id = $1").execute(Tuple.of(id))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    //在数据库中中增加一条数据
    public Uni<Long> add( Project project ) {
        return client.preparedQuery("INSERT INTO iiit_projects (name) VALUES ($1) RETURNING (id)").execute(Tuple.of(project.name))
                .onItem().transform(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }

    //在数据库中修改一条数据
    public Uni<Boolean> update (Project project ) {
        return client.preparedQuery("UPDATE iiit_projects SET name = $1 WHERE id = $2").execute(Tuple.of(project.name, project.id))
                .onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
    }

    //在数据库中删除一条数据
    public  Uni<Boolean> delete( Long id) {    	
        return client.preparedQuery("DELETE FROM iiit_projects WHERE id = $1").execute(Tuple.of(id))
                .onItem().transform(pgRowSet -> pgRowSet.rowCount() == 1);
    }

    //把一行数据组装成一个Project对象
    private  static Project from( Row row) {
        return new Project(row.getLong("id"), row.getString("name"));
    }
}
