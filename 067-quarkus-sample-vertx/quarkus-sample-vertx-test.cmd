

@REM ������������������������������accessvertx����������������������������
curl http://localhost:8080/vertx/bare
curl http://localhost:8080/vertx/mutiny
curl http://localhost:8080/vertx/reactivex
curl http://localhost:8080/vertx/axle
curl http://localhost:8080/vertx/mutiny/getfile


@REM ������������������������������webclient����������������������������
curl http://localhost:8080/vertx/webclient/1

@REM ������������������������������routes����������������������������
curl http://localhost:8080/route/projects
curl http://localhost:8080/route/getprojects
curl  http://localhost:8080/route/registar

@REM ������������������������������pgclient����������������������������
curl http://localhost:8080/projectpgs

curl http://localhost:8080/projectpgs/1

curl -X POST  -H "Content-type: application/json" -d {\"id\":5,\"name\":\"��ĿABC\"} http://localhost:8080/projectpgs

curl -X PUT  -H "Content-type: application/json" -d {\"id\":5,\"name\":\"��ĿABC�޸�\"} http://localhost:8080/projectpgs/5

curl -X DELETE http://localhost:8080/projectpgs/4 -v

@REM ������������������������������EventBus����������������������������
curl http://localhost:8080/eventbus/1
curl http://localhost:8080/eventbus/2


@REM ������������������������������delay����������������������������
curl http://localhost:8080/vertx/delay/1

@REM ������������������������������json����������������������������
curl http://localhost:8080/vertx/json/object/reng
curl http://localhost:8080/vertx/json/array/reng


@REM ������������������������������stream����������������������������
curl http://localhost:8080/stream/reng










