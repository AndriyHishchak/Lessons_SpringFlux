package SpringFlux.Start.Lesson_33_WebClient.controller;

import SpringFlux.Start.Lesson_33_WebClient.Service.CastomerImpl;
import SpringFlux.Start.Lesson_33_WebClient.model.Castomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
@RestController
@RequestMapping(value = "/service/v2",produces = "application/json")
public class controller {
    private WebClient webClient;
    private CastomerImpl servise;
    @Autowired
    public controller(CastomerImpl servise) {
        this.servise = servise;
    }

    @PostConstruct
    private void setUPWebClient() {
        webClient = WebClient.create("http://localhost:8091");
    }

    @GetMapping("/{name}")
    public Mono<Castomer> getByName(@PathVariable("name") String name) {
           try {
            return webClient
                    .get()
                    .uri("/service/v1/{name}", name)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Castomer.class)
                    .log("Get Mapping ByName : ");
    }catch (WebClientResponseException ex) {
        System.out.println("Error Response Code is " + ex.getRawStatusCode() + " and the response body is "+ ex.getResponseBodyAsString());
        System.out.println("WebClientResponseExeption in getByName Castomers" + ex);
        throw ex;
    }catch (Exception ex) {
        System.out.println("Exception in get getByName Castomers" + ex);
        throw ex;
    }
}
    @GetMapping
    public Flux<Castomer> getCastomersAll() {
        return webClient
                .get()
                .uri("/service/v1/all") //-- по даному ендпоинту із сторонього сервіма
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMapMany(r -> r.bodyToFlux(Castomer.class))
                .log("GetAll :");
}

    @GetMapping("/all")
    public Flux<Castomer> getAll() {
            return webClient
                    .get()
                    .uri("/service/v1/all") //-- по даному ендпоинту із сторонього сервіма
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .flatMapMany(r -> r.bodyToFlux(Castomer.class))
                    .log("Get Mapping All :");
    }

    @DeleteMapping("/{name}")
    public Mono<Void> deleteByName(@PathVariable("name") String name) {
        try{
        return webClient
                .delete()
                .uri("/service/v1/{name}", name) //-- ендпоинт ыз сторонього сервіса
                .retrieve()
                .bodyToMono(Void.class);
    }catch (WebClientResponseException ex) {
        System.out.println("Error Response Code is " + ex.getRawStatusCode() + " and the response body is "+ ex.getResponseBodyAsString());
        System.out.println("WebClientResponseExeption in delete Castomer" + ex);
        throw ex;
    }catch (Exception ex) {
        System.out.println("Exception in delete Castomer" + ex);
        throw ex;
     }
    }

    @PostMapping
    public Mono<Castomer> create(@RequestBody Castomer castomer) {
        try {
            return webClient
                    .post()
                    .uri("/service/v1/")
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(castomer))
                    .retrieve()
                    .bodyToMono(Castomer.class)
                    .log("Post Mapping Create :");
        } catch (WebClientResponseException ex) {
            System.out.println("Error Response Code is " + ex.getRawStatusCode() + " and the response body is " + ex.getResponseBodyAsString());
            System.out.println("WebClientResponseExeption in create Castomer" + ex);
            throw ex;
        } catch (Exception ex) {
            System.out.println("Exception in create Castomer" + ex);
            throw ex;
        }
    }

    @PutMapping("/{name}")
    public Mono<Castomer> putUpdate (@RequestBody Castomer castomer,
                                     @PathVariable("name") String name) {
        return servise.Update(webClient,castomer, name);
    }

    @PatchMapping("/{name}")
    public Mono<Castomer> patchUpdate (@RequestBody Castomer castomer,
                                       @PathVariable("name") String name) {
        return servise.Update(webClient,castomer, name);
    }


}