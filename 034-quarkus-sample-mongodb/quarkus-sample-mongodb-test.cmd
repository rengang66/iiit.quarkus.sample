

curl http://localhost:8080/projects

curl http://localhost:8080/projects/find/A

curl -X POST -H "Content-type: application/json" -d {\"name\":\"��ĿC\",\"description\":\"������ĿC������\"} http://localhost:8080/projects

curl -X PUT -H "Content-type: application/json" -d {\"name\":\"��ĿC\",\"description\":\"������ĿC�������޸�\"} http://localhost:8080/projects

curl -X curl -X DELETE -H "Content-type: application/json" -d {\"name\":\"��ĿB\",\"description\":\"������ĿB�������޸�\"} http://localhost:8080/projects

pause




