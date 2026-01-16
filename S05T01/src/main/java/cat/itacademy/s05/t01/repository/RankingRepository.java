package cat.itacademy.s05.t01.repository;

import cat.itacademy.s05.t01.domain.model.PlayerRanking;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RankingRepository extends R2dbcRepository<PlayerRanking, Long> {
    Mono<PlayerRanking> findByName(String name);
}
