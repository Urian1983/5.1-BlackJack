package cat.itacademy.s05.t01.service;

import cat.itacademy.s05.t01.domain.GameState;
import cat.itacademy.s05.t01.domain.mongoDB.Game;
import cat.itacademy.s05.t01.dto.GameMapper;
import cat.itacademy.s05.t01.dto.GameResponseDTO;
import cat.itacademy.s05.t01.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final RankingService rankingService;
    public Mono<GameResponseDTO> createGame (String playerName){
        Game newGame = new Game(playerName);
        return gameRepository.save(newGame)
                .map(GameMapper::toResponse);
    }

    public Mono<GameResponseDTO> playHit (String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Game not found")))
                .flatMap(game -> {
            game.playerHit();
            return gameRepository.save(game);
        }).map(GameMapper::toResponse);
    }

    public Mono<GameResponseDTO> playStand(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Game not found")))
                .flatMap(game -> {
                    game.playerStand();
                    return gameRepository.save(game);
                })
                .map(GameMapper::toResponse);
    }
}
