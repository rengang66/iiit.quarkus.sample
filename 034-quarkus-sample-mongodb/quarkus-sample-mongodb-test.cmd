

curl http://localhost:8080/projects

curl http://localhost:8080/projects/find/A

curl -X POST -H "Content-type: application/json" -d {\"name\":\"项目C\",\"description\":\"关于项目C的描述\"} http://localhost:8080/projects

curl -X PUT -H "Content-type: application/json" -d {\"name\":\"项目C\",\"description\":\"关于项目C的描述修改\"} http://localhost:8080/projects

curl -X curl -X DELETE -H "Content-type: application/json" -d {\"name\":\"项目B\",\"description\":\"关于项目B的描述修改\"} http://localhost:8080/projects

pause




