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
package com.iiit.sample.security.openidconnect.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.oidc.IdToken;
import io.quarkus.oidc.RefreshToken;

@Path("/projects/tokens")
public class TokenResource {

    //由OpenID Connect Provider提供程序ID令牌的注入点
    @Inject
    @IdToken
    JsonWebToken idToken;
    
   //由OpenID Connect Provider提供程序访问令牌的注入点
    @Inject
    JsonWebToken accessToken;
    
    //由OpenID Connect Provider提供程序Refresh Token的注入点
    @Inject
    RefreshToken refreshToken;
   
    //返回一个map，包含了关于本程序的令牌信息
    @GET
    public String getTokens() {
        StringBuilder response = new StringBuilder().append("<html>")
                .append("<body>")
                .append("<ul>");

        Object userName = this.idToken.getClaim("preferred_username");
        if (userName != null) {
            response.append("<li>username: ").append(userName.toString()).append("</li>");
        }
        
        Object access_token = this.accessToken.getRawToken();        
        if (access_token != null) {        	
        	response.append("<li>access_token: ").append(access_token).append("</li>");        	
        }       
        

        Object scopes = this.accessToken.getClaim("scope");
        if (scopes != null) {
            response.append("<li>scopes: ").append(scopes.toString()).append("</li>");
        }

        response.append("<li>refresh_token: ").append(refreshToken.getToken() != null).append("</li>");            
        
        response.append("<li>refresh_token: ").append(refreshToken.getToken()).append("</li>");         

        return response.append("</ul>").append("</body>").append("</html>").toString();
    }
}
