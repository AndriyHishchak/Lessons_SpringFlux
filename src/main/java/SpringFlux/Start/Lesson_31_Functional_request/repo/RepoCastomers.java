package SpringFlux.Start.Lesson_31_Functional_request.repo;

import SpringFlux.Start.Lesson_31_Functional_request.model.Castomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RepoCastomers extends ReactiveMongoRepository<Castomer,String> {
    Mono<Castomer> findByName(String name);
}
