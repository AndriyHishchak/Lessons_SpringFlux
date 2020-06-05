package SpringFlux.Start.Lesson_0_SpringFlux;

import SpringFlux.Start.Lesson_0_SpringFlux.model.Castomer;
import SpringFlux.Start.Lesson_0_SpringFlux.repo.RepoCastomers;
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
						Stream.of(new Castomer(UUID.randomUUID().toString(),"Peret","Hishchak",23L))
						.forEach(castomer -> {
							repoCastomers.save(castomer)
							.subscribe(System.out::println);
						});

					});
		};
	}

}
