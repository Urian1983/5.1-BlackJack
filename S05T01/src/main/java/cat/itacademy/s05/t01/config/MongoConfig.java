package cat.itacademy.s05.t01.config;

import cat.itacademy.s05.t01.repository.mongodb.repository.GameRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(
        basePackageClasses = GameRepository.class
)
public class MongoConfig {
}
