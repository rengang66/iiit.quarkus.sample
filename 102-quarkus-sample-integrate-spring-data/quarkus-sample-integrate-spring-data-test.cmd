
curl http://localhost:8080/projects/

curl http://localhost:8080/projects/1

curl -X POST -H "Content-type: application/json" -d {\"id\":6,\"name\":\"��ĿF\",\"description\":\"������ĿF������\"} http://localhost:8080/projects/add

curl http://localhost:8080/projects/6

curl -X PUT -H "Content-type: application/json" -d {\"id\":6,\"name\":\"��ĿF\",\"description\":\"������ĿF���������޸�\"} http://localhost:8080/projects/update

curl -X DELETE http://localhost:8080/projects/5

pause