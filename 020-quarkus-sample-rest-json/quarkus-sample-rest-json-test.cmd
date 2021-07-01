
curl http://localhost:8080/projects

curl http://localhost:8080/projects/1

curl -X POST -H "Content-type: application/json" -d {\"id\":3,\"name\":\"项目C\",\"description\":\"关于项目C的描述\"} http://localhost:8080/projects

curl -X PUT -H "Content-type: application/json" -d {\"id\":3,\"name\":\"项目C\",\"description\":\"项目C描述修改内容\"} http://localhost:8080/projects

curl -X DELETE  -H "Content-type: application/json" -d {\"id\":3,\"name\":\"项目C\",\"description\":\"关于项目C的描述\"} http://localhost:8080/projects

pause

