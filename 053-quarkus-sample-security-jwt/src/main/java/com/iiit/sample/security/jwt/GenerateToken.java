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
package com.iiit.sample.security.jwt;

import java.util.Arrays;
import java.util.HashSet;

import javax.inject.Inject;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import io.smallrye.jwt.build.Jwt;

/**
 * A simple utility class to generate and print a JWT token string to stdout.
 */
public class GenerateToken {	
	
    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
    	String token = Jwt.issuer("https://www.iiit.com")
    	   .upn("rengang66@sina.com")
    	   .groups(new HashSet<>(Arrays.asList("User", "Admin")))
    	   .claim(Claims.birthdate.name(), "1971-05-06")
    	   .sign();
        System.out.println(token);
    }
}
