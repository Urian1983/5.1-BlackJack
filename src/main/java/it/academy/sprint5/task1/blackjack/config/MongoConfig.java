package it.academy.sprint5.task1.blackjack.config;

import it.academy.sprint5.task1.blackjack.repository.mongodb.GameRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = GameRepository.class)
public class MongoConfig {
}
