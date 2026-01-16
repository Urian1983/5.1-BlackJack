package cat.itacademy.s05.t01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@EnableR2dbcRepositories(basePackageClasses = cat.itacademy.s05.t01.repository.RankingRepository.class)
@EnableReactiveMongoRepositories(basePackageClasses = cat.itacademy.s05.t01.repository.GameRepository.class)
@SpringBootApplication
public class S05T01Application {

	public static void main(String[] args) {

		SpringApplication.run(S05T01Application.class, args);
	}

}
