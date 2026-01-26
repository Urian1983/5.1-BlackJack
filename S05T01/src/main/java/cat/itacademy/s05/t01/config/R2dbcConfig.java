package cat.itacademy.s05.t01.config;

import cat.itacademy.s05.t01.repository.RankingRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(
        basePackageClasses = RankingRepository.class
)
public class R2dbcConfig {
}