package SpringFlux.Start.Lesson_0_SpringFlux.controller;

import SpringFlux.Start.Lesson_0_SpringFlux.model.Castomer;
import SpringFlux.Start.Lesson_0_SpringFlux.model.CastomerEvent;
import SpringFlux.Start.Lesson_0_SpringFlux.repo.RepoCastomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/castomer")
public class controller {

    RepoCastomers repoCastomers;
    @Autowired
    public controller(RepoCastomers repoCastomers) {
        this.repoCastomers = repoCastomers;
    }

    @GetMapping("/all")
    public Flux<Castomer> getAll(){
        return repoCastomers.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Castomer> getId(@PathVariable("id") final String id) {
        return repoCastomers.findById(id);
    }

    @GetMapping(value = "/{id}/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CastomerEvent> getEvent(@PathVariable("id") final String empId) {

        return repoCastomers.findById(empId)
                .flatMapMany(castomer -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                    Flux<CastomerEvent> EventFlux = Flux.fromStream(Stream.generate(() -> new CastomerEvent(castomer,
                            new Date()))
                    );
        return Flux.zip(interval,EventFlux).map(Tuple2::getT2);
                });
                }

}
