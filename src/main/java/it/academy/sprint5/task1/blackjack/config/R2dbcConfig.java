package it.academy.sprint5.task1.blackjack.config;

import it.academy.sprint5.task1.blackjack.repository.RankingRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackageClasses = RankingRepository.class)
public class R2dbcConfig {
}
