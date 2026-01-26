package cat.itacademy.s05.t01;

import cat.itacademy.s05.t01.repository.mongodb.repository.GameRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


//@EnableR2dbcRepositories("cat.itacademy.s05.t01.repository")
//@EnableReactiveMongoRepositories("cat.itacademy.s05.t01.repository.mongodb.repository")
@SpringBootApplication
public class S05T01Application {

	public static void main(String[] args) {

		SpringApplication.run(S05T01Application.class, args);
	}

}
