package SpringFlux.Start.Lesson_0_SpringFlux.repo;

import SpringFlux.Start.Lesson_0_SpringFlux.model.Castomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCastomers extends ReactiveMongoRepository<Castomer,String> {
}
