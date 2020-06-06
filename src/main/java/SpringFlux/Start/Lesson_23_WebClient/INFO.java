package SpringFlux.Start.Lesson_23_WebClient;

public class INFO {
    //TODO:     ********************************************
    //TODO:     * Мета: * Створити запити на всторонэ АPI  *
    //TODO:     ********************************************
    //TODO:     WebClient - це клас який мыстить методи для конекту із стороніми API
    //TODO:         .create - метод WebClient для створення конекта із сторонім API
    //TODO:         Приклад : WebClient.create("Http://localhost:4000");
    //TODO:     --------------------------------------------------------------------
    //TODO:     Створюэмо едппоинт
    //TODO:     @GetMapping
    //TODO:     public Mono<Class> getById (@PathVariable("id")String id) {
    //TODO:         return webClient
    //TODO:                     .get()
    //TODO:                     .uri("api/{id}") -- по даному ендпоинту із сторонього сервіма
    //TODO:                     .retrieve() -- достати тіло
    //TODO:                     .bodyToMono(Class.class) -- передаэмо обэкт
    //TODO:                     .map(Class::getData)}    -- ?
    //TODO:     @DeleteMapping("{id}/delete")
    //TODO:     public Mono<Void> deleteById(@PathVariable("id") String id) {
    //TODO:         return webClient
    //TODO:                     .delete()
    //TODO:                     .uri("api/{id}") -- ендпоинт ыз сторонього сервіса
    //TODO:                     .retrieve()
    //TODO:                     .bodyToMono(Void.class)
    //TODO:     @GetMapping("/create")
    //TODO:     public Mono<Class> create() {
    //TODO:         return
    //TODO:             .post()
    //TODO:             .uri("api/...")
    //TODO:             .bodyToMono(new Class(...,...,...))
    //TODO:             .exchenge()
    //TODO:             .flatMap(r -> r.bodeToMono(Class::getData).map(Сlass::getData))
    //TODO:
    //TODO:     @GetMapping("/{id}/edit")
    //TODO:     public Mono<Class> (@PathVariable("id") String id) {
    //TODO:     return webClient
    //TODO:                     .put()
    //TODO:                     .uri("api/{id}") -- ендпоинт ыз сторонього сервіса
    //TODO:                     .bodyToMono(Mono.just(new Class(...,...,...,...)))
    //TODO:                     .retrieve()
    //TODO:                     .bodyToMono(Class.class)
    //TODO:                     .map(Class::getData())
    //TODO:
    //TODO:
    //TODO:
    //TODO:


}
