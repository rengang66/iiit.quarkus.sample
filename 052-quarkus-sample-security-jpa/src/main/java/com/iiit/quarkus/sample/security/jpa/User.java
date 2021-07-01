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
package com.iiit.quarkus.sample.security.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@Table(name = "test_user")
@UserDefinition
public class User {
    
	@Id
	private Integer id;
	
	@Username
	@Column(length = 255)
    private String username;
	
    @Password
    @Column(length = 255)
    private String password;
    
    @Roles
    @Column(length = 255)
    private String role;

    
    public User() {}
    
    public User(Integer id,String username, String password, String role)
    {
    	this.id = id;
    	this.username = username;
    	this.password = BcryptUtil.bcryptHash(password);
    	this.role = role;    	
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
    
    public String getPassword() {    	
    	
        return this.password;
    }

    public void setPassword(String password) {
    	
       // this.password = password;
    	 this.password = BcryptUtil.bcryptHash(password);
    }
    
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }    
}
