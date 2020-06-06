package SpringFlux.Start.Lesson_21_WebFlux_Rest_service;

import SpringFlux.Start.Lesson_21_WebFlux_Rest_service.model.Castomer;
import SpringFlux.Start.Lesson_21_WebFlux_Rest_service.repo.RepoCastomers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Bean
	CommandLineRunner castomer(RepoCastomers repoCastomers) {
		return args -> {
			repoCastomers.deleteAll()
					.subscribe(null,null,() -> {
						Stream.of(new Castomer(UUID.randomUUID().toString(),"Peret","Hishchak",23L),
								new Castomer(UUID.randomUUID().toString(),"Taras","Supruk",22L),
						new Castomer(UUID.randomUUID().toString(),"Oleg","Berezanskiy",23L),
						new Castomer(UUID.randomUUID().toString(),"Stanislaw","Ivasiv",12L),
						new Castomer(UUID.randomUUID().toString(),"Roman","Yaworskiy",24L))
						.forEach(castomer -> {
							repoCastomers.save(castomer)
							.subscribe(System.out::println);
						});

					});
		};
	}

}
