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
package com.iiit.quarkus.sample.jta;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.ws.rs.WebApplicationException;
import javax.transaction.Transaction;
import org.jboss.logging.Logger;

@ApplicationScoped
public class TransactionProjectService {

    private static final Logger LOGGER = Logger.getLogger(TransactionProjectService.class.getName());

    @Inject 
    TransactionManager transactionManager;    
    
    //注入持久类
    @Inject
    EntityManager entityManager;  
    
   // private Transaction tx = null;
           
    //获取所有Project列表
    public List<Project> get() {
        return entityManager.createNamedQuery("Projects.findAll", Project.class)
                .getResultList();
    }
     
    //获取单个Project
    public Project getSingle(Integer id) { 
        Project entity = entityManager.find(Project.class, id);
        if (entity == null) {
        	String info  = "project with id of " + id + " does not exist.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 404);
        }
        return entity;
    }
  
    //带事务提交增加一条记录   
    public Project add(Project project) throws SystemException {
        if (project.getId() == null) {
        	String info  = "Id was invalidly set on request.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 422);
        } 
        Transaction tx = null;
        
        try {
        	transactionManager.begin();
        	tx = transactionManager.getTransaction();        	
        	entityManager.persist(project);         	
        	tx.commit();
        	//transactionManager.getTransaction().commit();
        	System.out.println("add 成功!");
        } catch (Exception e) {        	
        	tx.rollback();
        	//transactionManager.getTransaction().rollback();
        	System.out.println("add 不成功!");
            e.printStackTrace();
        }    
        transactionManager.suspend();
        return project;
    }   
  
    //带事务提交修改一条记录   
    public Project update(Project project)  throws SystemException {
        if (project.getName() == null) {
        	String info  = "project Name was not set on request.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 422);
        }

        Project entity = entityManager.find(Project.class, project.getId());
        if (entity == null) {
        	String info  = "project with id  does not exist.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 404);
        }        
        Transaction tx = null;
        try {
        	transactionManager.begin();
        	tx = transactionManager.getTransaction();
        	entity.setName(project.getName());        	
        	entityManager.merge(entity);        	
        	tx.commit();
        	//transactionManager.getTransaction().commit();
        	 System.out.println("update 成功!");
        } catch (Exception e) {
        	tx.rollback(); 
        	//transactionManager.getTransaction().rollback();
        	System.out.println("update 不成功!");
            e.printStackTrace();           
        }     
        transactionManager.suspend();
        return entity;
    }

    //带事务提交删除一条记录   
    public void delete( Integer id) throws SystemException  {    	
    	Project entity = entityManager.find(Project.class, id);
        if (entity == null) {
        	String info  = "project with id of " + id + " does not exist.";
        	LOGGER.info(info);
            throw new WebApplicationException(info, 404);
        }
        Transaction tx = null;
        try {
        	transactionManager.begin(); 
        	tx = transactionManager.getTransaction();
        	entityManager.remove(entityManager.getReference(Project.class, id));         	
        	tx.commit();
        	//transactionManager.getTransaction().commit();
        	System.out.println("delete 成功");
        } catch (Exception e) {
        	//transactionManager.getTransaction().rollback();
        	tx.rollback();    
            e.printStackTrace();  
        	 System.out.println("无法删除，delete!");
        }       
        transactionManager.suspend();
        return ;
    }        
   
}
