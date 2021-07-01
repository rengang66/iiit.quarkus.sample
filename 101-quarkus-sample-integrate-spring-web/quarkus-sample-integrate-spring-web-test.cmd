
curl http://localhost:8080/projects/

curl http://localhost:8080/projects/1


curl -X POST -H "Content-type: application/json" -d {\"id\":4,\"name\":\"项目D\",\"description\":\"关于项目D的描述\"} http://localhost:8080/projects/add

curl http://localhost:8080/projects/4

curl -X PUT -H "Content-type: application/json" -d {\"id\":4,\"name\":\"项目D\",\"description\":\"关于项目D的描述的修改\"} http://localhost:8080/projects/update


curl -X DELETE  -H "Content-type: application/json" -d {\"id\":3,\"name\":\"项目C\",\"description\":\"关于项目C的描述\"} http://localhost:8080/projects/delete

pause