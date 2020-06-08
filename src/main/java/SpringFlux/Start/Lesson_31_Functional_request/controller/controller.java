package SpringFlux.Start.Lesson_31_Functional_request.controller;

import SpringFlux.Start.Lesson_31_Functional_request.model.Castomer;
import SpringFlux.Start.Lesson_31_Functional_request.repo.RepoCastomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/service/v1",produces = "application/json")
public class controller {

    private ResponseEntity Not_Found = ResponseEntity.notFound().build();
    private RepoCastomers repoCastomers;

    @Autowired
    public controller(RepoCastomers repoCastomers) {
        this.repoCastomers = repoCastomers;
    }

    @GetMapping("/all")
    public Flux<Castomer> getAll(){
        return repoCastomers
                .findAll()
                .log("Find All :");
    }


    @GetMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity> getUserName (@PathVariable("userName") String userName) {
        return  repoCastomers.findByName(userName)
                .map(cast -> new ResponseEntity<Object>(cast,HttpStatus.OK)).defaultIfEmpty(Not_Found)
                .log("FindByName :");
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Castomer> createUser(@RequestBody Castomer castomer) {
        return repoCastomers
                .save(castomer)
                .log("Save :");
    }


    @DeleteMapping("/{name}")
    public Mono<ResponseEntity<Void>> delete (@PathVariable("name") String name) {
        return repoCastomers.findByName(name)
                .flatMap(existingUser -> repoCastomers.delete(existingUser)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND))
                .log("Delete : ");
    }

    @PutMapping ("/{name}")
    public Mono<ResponseEntity<Castomer>> putUpdate (@PathVariable("name")String name,
                                                  @RequestBody Castomer castomer) {
        return getResponseEntityMono(name, castomer);
    }

    @PatchMapping("/{name}")
    public Mono<ResponseEntity<Castomer>> patchUpdate (@PathVariable("name")String name,
                                                  @RequestBody Castomer castomer) {
        return getResponseEntityMono(name, castomer);
    }


    private Mono<ResponseEntity<Castomer>> getResponseEntityMono(@PathVariable("name") String name, @RequestBody Castomer castomer) {
        return repoCastomers.findByName(name)
                .flatMap(existCastomer -> {
                    if(castomer.getName() !=null) existCastomer.setName(castomer.getName());
                    if(castomer.getSurname() !=null) existCastomer.setSurname(castomer.getSurname());
                    if(castomer.getAge() !=null) existCastomer.setAge(castomer.getAge());
                    return repoCastomers.save(existCastomer);
                }).map(updateCastomer -> new ResponseEntity<>(updateCastomer, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND))
                .log("Patch Update :");
    }
}
