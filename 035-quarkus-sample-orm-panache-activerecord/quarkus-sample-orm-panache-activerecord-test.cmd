
curl http://localhost:8080/projects/

curl http://localhost:8080/projects/2

curl -X POST -d  {\"name\":\"��ĿD\"} -H "Content-Type:application/json"  http://localhost:8080/projects -v

curl -X PUT -H "Content-type: application/json" -d {\"name\":\"��ĿBBB\"} http://localhost:8080/projects/2

curl -X DELETE http://localhost:8080/projects/4

pause



