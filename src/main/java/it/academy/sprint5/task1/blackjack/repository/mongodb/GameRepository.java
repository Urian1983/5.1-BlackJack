package it.academy.sprint5.task1.blackjack.repository.mongodb;

import it.academy.sprint5.task1.blackjack.domain.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends ReactiveMongoRepository<Game,String> {
}
