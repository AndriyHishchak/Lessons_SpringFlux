package SpringFlux.Start.Lesson_21_WebFlux_Rest_service;

public class INFO {

    //TODO:     **************************
    //TODO:     * Мета: * Створити Rest-service SpringFlux *
    //TODO:     **************************
    //TODO:     Reactive - це парадигма основана на моделі яка сліжкує на моделі розповлюдження
    //TODO:     Async - це коли порядок виконання кода не извесний (1-5-3-2-4)
    //TODO:     ------------------------------------------------------------------------------------------
    //TODO:     Reactive Specification
    //TODO:     Publisher - інтерфес який дозволяє створити поток собитий, у якогось паблішера який видає стрім обєетів
    //TODO:     Subscriber - інтерфейс який підписується на паблішера, і він знає що робити із кожним входящим елементом який паблішер створює.
    //TODO:               onSubscriber - метод у якоий передаэ Subscribshen
    //TODO:     Mono<T> - (працюэ із 0...1 обєктом) це реалызація project react яка поверне 0 або 1,щось повернеться або не повернеться
    //TODO:     Flux<T> - (працюэ із 0...N обєктом) це асинхронний потік,черга обєктів, він позволить реалізувати реактивінсть за допомогою Flux API
    //TODO:     Flux API - методи
    //TODO:     Backpressure - проблема асихронності
    //TODO:     ------------------------------------------------------------------------------------
    //TODO:     HandlerFunction<ServerResponse> - метод ответа
    //TODO:         ServerResponse.ok() - 200OK
    //TODO:                       .contentType(MediaType.APPLICATION_JSON) - передавати JSON
    //TODO:                       .body() - передача обэкта
    //TODO:     ------------------------------------------------------------------------------------------
    //TODO:     RouterFunction<ServerResponse> - роутер ендпоинтів
    //TODO:     Є 2 способів опису роутера
    //TODO:     1) getAll - це метод описаний внизу
    //TODO:        route(GET("/"),this::getAll)
    //TODO:                private Mono<ServerResponse> getAll(ServerRequest request) {
    //TODO:                return ServerResponse.ok().body(repoCastomers.findAll(),Castomer.class);
    //TODO:    2) Лямда-вираз
    //TODO:     route(GET("/{name}"),serverRequest -> ok().body(repoCastomers.findByName(serverRequest.pathVariable("name")),Castomer.class))
    //TODO:     -----------------------------------------------------------------------------------------------
    //TODO:

}
