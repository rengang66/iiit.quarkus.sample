
curl -X POST http://localhost:8180/auth/realms/quarkus/protocol/openid-connect/token ^
--user backend-service:secret ^
-H "content-type: application/x-www-form-urlencoded" ^
-d "username=admin&password=admin&grant_type=password"

curl -X POST http://localhost:8180/auth/realms/quarkus/protocol/openid-connect/token ^
--user backend-service:secret ^
-H "content-type: application/x-www-form-urlencoded" ^
-d "username=rengang&password=123456&grant_type=password"


http://localhost:8080/projects/api/admin

http://localhost:8080/projects/api/users/user






