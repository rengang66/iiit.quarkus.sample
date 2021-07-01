

@REM ¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªaccessvertx¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª
curl http://localhost:8080/vertx/bare
curl http://localhost:8080/vertx/mutiny
curl http://localhost:8080/vertx/reactivex
curl http://localhost:8080/vertx/axle
curl http://localhost:8080/vertx/mutiny/getfile


@REM ¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªwebclient¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª
curl http://localhost:8080/vertx/webclient/1

@REM ¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªroutes¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª
curl http://localhost:8080/route/projects
curl http://localhost:8080/route/getprojects
curl  http://localhost:8080/route/registar

@REM ¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªpgclient¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª
curl http://localhost:8080/projectpgs

curl http://localhost:8080/projectpgs/1

curl -X POST  -H "Content-type: application/json" -d {\"id\":5,\"name\":\"ÏîÄ¿ABC\"} http://localhost:8080/projectpgs

curl -X PUT  -H "Content-type: application/json" -d {\"id\":5,\"name\":\"ÏîÄ¿ABCÐÞ¸Ä\"} http://localhost:8080/projectpgs/5

curl -X DELETE http://localhost:8080/projectpgs/4 -v

@REM ¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªEventBus¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª
curl http://localhost:8080/eventbus/1
curl http://localhost:8080/eventbus/2


@REM ¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªdelay¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª
curl http://localhost:8080/vertx/delay/1

@REM ¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªjson¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª
curl http://localhost:8080/vertx/json/object/reng
curl http://localhost:8080/vertx/json/array/reng


@REM ¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªstream¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª
curl http://localhost:8080/stream/reng










