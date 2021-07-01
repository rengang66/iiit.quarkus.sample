
curl http://localhost:8080/projects/

curl http://localhost:8080/projects/1

curl -X POST -H "Content-type: application/json" -d {\"id\":6,\"name\":\"项目F\",\"description\":\"关于项目F的描述\"} http://localhost:8080/projects/add

curl http://localhost:8080/projects/6

curl -X PUT -H "Content-type: application/json" -d {\"id\":6,\"name\":\"项目F\",\"description\":\"关于项目F的描述的修改\"} http://localhost:8080/projects/update

curl -X DELETE http://localhost:8080/projects/5

pause