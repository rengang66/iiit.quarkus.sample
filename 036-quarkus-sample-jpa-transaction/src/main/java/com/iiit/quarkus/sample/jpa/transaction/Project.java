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
package com.iiit.quarkus.sample.jpa.transaction;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "iiit_projects")
@NamedQuery(name = "Projects.findAll", query = "SELECT f FROM Project f ORDER BY f.name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Project {

    @Id
    //@SequenceGenerator(name = "projectsSequence", sequenceName = "iiit_projects_id_seq", allocationSize = 1, initialValue = 10)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectsSequence")
    private Integer id;

    @Column(length = 40, unique = true)
    private String name;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }
    
    public Project(Integer id,String name) {
    	this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
