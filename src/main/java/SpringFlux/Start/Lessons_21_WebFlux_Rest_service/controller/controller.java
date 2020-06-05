package SpringFlux.Start.Lessons_21_WebFlux_Rest_service.controller;

import SpringFlux.Start.Lessons_21_WebFlux_Rest_service.model.Castomer;
import SpringFlux.Start.Lessons_21_WebFlux_Rest_service.repo.RepoCastomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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


    @GetMapping("/{userName}")
    public Mono<Castomer> getUserName (@PathVariable("userName") String userName) {
        return repoCastomers.findByName(userName);
    }
    @PostMapping(value = "/create",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Castomer> createUser(@RequestBody Mono<Castomer> castomerMono) {
        return repoCastomers.saveAll(castomerMono).next();
    }


    @DeleteMapping("/delete/{name}")
    public Mono<ResponseEntity<Void>> delete (@PathVariable("name") String name) {
        return repoCastomers.findByName(name)
                .flatMap(existingUser -> repoCastomers.delete(existingUser)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/update/{name}")
    public Mono<ResponseEntity<Castomer>> update (@PathVariable("name")String name,
                                              @RequestBody Castomer castomer) {
        return repoCastomers.findByName(name)
                .flatMap(existCastomer -> {
                    if(castomer.getName() !=null) existCastomer.setName(castomer.getName());
                    if(castomer.getSurname() !=null) existCastomer.setSurname(castomer.getSurname());
                    if(castomer.getAge() !=null) existCastomer.setAge(castomer.getAge());
                    return repoCastomers.save(existCastomer);
                }).map(updateCastomer -> new ResponseEntity<>(updateCastomer,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
