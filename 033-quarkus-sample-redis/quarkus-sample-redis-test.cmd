

curl http://localhost:8080/projects/project1

curl -X POST  -H "Content-type: application/json" -d {\"name\":\"project3\",\"description\":\"����project3������\"} http://localhost:8080/projects

curl -X PUT -H "Content-type: application/json" -d {\"name\":\"project2\",\"description\":\"����project2���������޸�\"} http://localhost:8080/projects/project2

curl -X DELETE http://localhost:8080/projects/project3  -v

pause