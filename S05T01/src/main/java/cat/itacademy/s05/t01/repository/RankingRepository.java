package cat.itacademy.s05.t01.repository;

import cat.itacademy.s05.t01.domain.sql.PlayerRanking;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RankingRepository extends R2dbcRepository<PlayerRanking,Long> {
    Flux<PlayerRanking> findAllByOrderByGamesWonDesc();
}
