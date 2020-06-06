package SpringFlux.Start.Lesson_21_WebFlux_Rest_service.repo;

import SpringFlux.Start.Lesson_21_WebFlux_Rest_service.model.Castomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RepoCastomers extends ReactiveMongoRepository<Castomer,String> {
    Mono<Castomer> findByName(String name);
}
