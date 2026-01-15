package cat.itacademy.s05.t01.service;

import cat.itacademy.s05.t01.domain.Game;
import cat.itacademy.s05.t01.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Mono<Game> playHit (String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Game not found")))
                .flatMap(game -> {
            game.playerHit();
            return gameRepository.save(game);
        });
    }

    public Mono<Game> playStand (String id){
        return gameRepository.findById(id).switchIfEmpty(Mono.error(new RuntimeException("Game not found")))
                .flatMap(game->{game.playerStand();
                return gameRepository.save(game);});
    }
}
