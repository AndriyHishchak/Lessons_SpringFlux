package SpringFlux.Start.Lesson_33_WebClient.Service;

import SpringFlux.Start.Lesson_33_WebClient.model.Castomer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class CastomerImpl {

    public Mono<Castomer> Update(WebClient webClient, @RequestBody Castomer castomer, @PathVariable("name") String name) {
        try {
            return webClient
                    .put()
                    .uri("/service/v1/{name}",name)
                    .accept(MediaType.APPLICATION_JSON)
                    .syncBody(castomer)
                    .retrieve()
                    .bodyToMono(Castomer.class)
                    .log("Put Mapping Update :");
        }catch (WebClientResponseException ex) {
            System.out.println("Error Response Code is " + ex.getRawStatusCode() + " and the response body is "+ ex.getResponseBodyAsString());
            System.out.println("WebClientResponseExeption in update Castomer" + ex);
            throw ex;
        }catch (Exception ex) {
            System.out.println("Exception in update Castomer" + ex);
            throw ex;
        }
    }


}
