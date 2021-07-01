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
import javax.transaction.SystemException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

@Path("projects")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProjectTransactionResource {

    private static final Logger LOGGER = Logger.getLogger(ProjectTransactionResource.class.getName());

  //注入服务类
    @Inject
    UserTransactionProjectService userTransactionService; 
    
    //注入服务类
    @Inject
    JpaProjectService jpaProjectService;    
    
    @Inject
    TransactionManagerProjectService transactionManagerProjectService;  
    
    @Inject
    TransactionProjectService transactionProjectService;  
    
    @GET 
    @Path("/usertransaction") 
    public void doUserTransaction()  throws SystemException { 
    	LOGGER.info("UserTransaction开始");
    	
    	LOGGER.info("增加单个数据");
    	Project project1 = new Project(6,"项目F");
    	userTransactionService.add(project1) ;     	
    	System.out.println(getProjectInform(jpaProjectService.get()));
    	
    	LOGGER.info("修改单个数据");
    	Project project2 = new Project(3,"项目C修改");
    	userTransactionService.update(project2);
    	System.out.println(getProjectInform(jpaProjectService.get()));
    	
    	LOGGER.info("删除单个数据");
    	userTransactionService.delete(6);    
    	System.out.println(getProjectInform(jpaProjectService.get()));
    	return ;
    }
    
    
    @GET 
    @Path("/jpaTransaction") 
    public void doJpaTransaction()  throws SystemException { 
    	LOGGER.info("jpaTransaction开始");
    	
    	LOGGER.info("获取所有数据");    	
    	System.out.println(getProjectInform(jpaProjectService.get()));
    	
    	LOGGER.info("获取单个数据");
    	Project project = jpaProjectService.getSingle(1);
    	String projectInform = "{项目ID：" + project.getId() + "，" + "项目名称："
				+ project.getName() + "};";
    	System.out.println(projectInform);
    	
    	LOGGER.info("增加单个数据");
    	Project project1 = new Project(6,"项目F");
    	jpaProjectService.add(project1) ;     	
    	System.out.println(getProjectInform(jpaProjectService.get()));
    	
    	LOGGER.info("修改单个数据");
    	Project project2 = new Project(3,"项目C修改");
    	jpaProjectService.update(project2);
    	System.out.println(getProjectInform(jpaProjectService.get()));
    	
    	LOGGER.info("删除单个数据");
    	jpaProjectService.delete(6);    
    	System.out.println(getProjectInform(jpaProjectService.get()));
    	return ;
    }
    
    @GET 
    @Path("/transactionmanager") 
    public void doTransactionManagerProjectService()  throws SystemException { 
    	LOGGER.info("transactionmanager开始");
    	
    	LOGGER.info("获取所有数据");    	
    	System.out.println(getProjectInform(transactionManagerProjectService.get()));
    	
    	LOGGER.info("获取单个数据");
    	Project project = transactionManagerProjectService.getSingle(1);
    	String projectInform = "{项目ID：" + project.getId() + "，" + "项目名称："
				+ project.getName() + "};";
    	System.out.println(projectInform);
    	
    	LOGGER.info("增加单个数据");
    	Project project1 = new Project(6,"项目F");
    	transactionManagerProjectService.add(project1) ;     	
    	System.out.println(getProjectInform(transactionManagerProjectService.get()));
    	
    	LOGGER.info("修改单个数据");
    	Project project2 = new Project(3,"项目C修改");
    	transactionManagerProjectService.update(project2);
    	System.out.println(getProjectInform(transactionManagerProjectService.get()));
    	
    	LOGGER.info("删除单个数据");
    	transactionManagerProjectService.delete(6);    
    	System.out.println(getProjectInform(transactionManagerProjectService.get()));
    	return ;
    }
    
    @GET 
    @Path("/transaction") 
    public void doTransactionProjectService()  throws SystemException { 
    	LOGGER.info("transaction开始");
    	
    	LOGGER.info("获取所有数据");    	
    	System.out.println(getProjectInform(transactionProjectService.get()));
    	
    	LOGGER.info("获取单个数据");
    	Project project = transactionProjectService.getSingle(1);
    	String projectInform = "{项目ID：" + project.getId() + "，" + "项目名称："
				+ project.getName() + "};";
    	System.out.println(projectInform);
    	
    	
    	LOGGER.info("增加单个数据");
    	Project project1 = new Project(6,"项目F");
    	transactionProjectService.add(project1) ;     	
    	System.out.println(getProjectInform(transactionProjectService.get()));
    	
    	
    	LOGGER.info("修改单个数据");
    	Project project2 = new Project(3,"项目C修改");
    	transactionProjectService.update(project2);
    	System.out.println(getProjectInform(transactionProjectService.get()));
    	
    	
    	LOGGER.info("删除单个数据");
    	transactionProjectService.delete(4);    
    	System.out.println(getProjectInform(transactionProjectService.get()));
    	return ;
    }
    
    @GET 
    @Path("/transaction/add") 
    public void doAddTransactionProjectService()  throws SystemException { 
    	LOGGER.info("增加单个数据");
    	Project project1 = new Project(6,"项目F");
    	transactionProjectService.add(project1) ;     	
    	System.out.println(getProjectInform(transactionProjectService.get()));
    }
    
    @GET 
    @Path("/transaction/update") 
    public void doUpdateTransactionProjectService()  throws SystemException { 
    	LOGGER.info("修改单个数据");
    	Project project2 = new Project(3,"项目C修改");
    	transactionProjectService.update(project2);
    	System.out.println(getProjectInform(transactionProjectService.get()));
    }
    
    @GET 
    @Path("/transaction/delete") 
    public void doDeleteTransactionProjectService()  throws SystemException { 
    	LOGGER.info("删除单个数据");
    	transactionProjectService.delete(4);    
    	System.out.println(getProjectInform(transactionProjectService.get()));
    	return ;
    }
    
    
    private String getProjectInform(List projects){    	
    	String projectContent = "";
    	for (int i = 0; i < projects.size(); i++) {    		
    		Project project = (Project) projects.get(i);    		
    		String projectInform = "{项目ID：" + project.getId() + "，" + "项目名称："
					+ project.getName() + "};";
			projectContent = projectContent + projectInform;            
        }    	
    	return projectContent;
    }  
    
}
