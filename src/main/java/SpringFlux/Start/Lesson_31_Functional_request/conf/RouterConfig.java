package SpringFlux.Start.Lesson_31_Functional_request.conf;

import SpringFlux.Start.Lesson_31_Functional_request.model.Castomer;
import SpringFlux.Start.Lesson_31_Functional_request.repo.RepoCastomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

//import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;


@Configuration
public class RouterConfig {

    private RepoCastomers repoCastomers;

    @Autowired
    public RouterConfig(RepoCastomers repoCastomers) {
        this.repoCastomers = repoCastomers;
    }

    @Bean
    public RouterFunction<?> routerFunction() {
        return
                route(GET("/"),this::getAll)
                .andRoute(GET("/{name}"),serverRequest -> ok().body(repoCastomers.findByName(serverRequest.pathVariable("name")),Castomer.class))
                .andRoute(POST("/"),this::createCastomer)
                .andRoute(DELETE("/delete/{name}"), serverRequest -> repoCastomers.findByName(serverRequest.pathVariable("name"))
                .flatMap(I -> noContent().build(repoCastomers.delete(I)))
                .switchIfEmpty(notFound().build()));

    }


    private Mono<ServerResponse> createCastomer(ServerRequest request) {
        Mono<Castomer> castomerPost = request.bodyToMono(Castomer.class); //дістаємо дані, достаємо як реквест боді
        Mono<Castomer> castomerSave = repoCastomers.saveAll(castomerPost).next();
        return ServerResponse.ok().body(castomerSave,Castomer.class);
    }


    private Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok().body(repoCastomers.findAll(),Castomer.class);
    }

}
