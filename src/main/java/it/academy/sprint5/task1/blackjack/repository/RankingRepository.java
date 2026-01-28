package it.academy.sprint5.task1.blackjack.repository;

import it.academy.sprint5.task1.blackjack.domain.model.PlayerRanking;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RankingRepository extends R2dbcRepository<PlayerRanking, Long> {
    Mono<PlayerRanking> findByName(String name);
}
