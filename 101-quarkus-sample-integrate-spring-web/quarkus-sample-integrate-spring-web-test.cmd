
curl http://localhost:8080/projects/

curl http://localhost:8080/projects/1


curl -X POST -H "Content-type: application/json" -d {\"id\":4,\"name\":\"��ĿD\",\"description\":\"������ĿD������\"} http://localhost:8080/projects/add

curl http://localhost:8080/projects/4

curl -X PUT -H "Content-type: application/json" -d {\"id\":4,\"name\":\"��ĿD\",\"description\":\"������ĿD���������޸�\"} http://localhost:8080/projects/update


curl -X DELETE  -H "Content-type: application/json" -d {\"id\":3,\"name\":\"��ĿC\",\"description\":\"������ĿC������\"} http://localhost:8080/projects/delete

pause