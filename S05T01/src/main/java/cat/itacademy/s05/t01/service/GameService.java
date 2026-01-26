package cat.itacademy.s05.t01.service;

import cat.itacademy.s05.t01.domain.Game;
import cat.itacademy.s05.t01.domain.GameState;
import cat.itacademy.s05.t01.exceptions.GameNotFoundException;
import cat.itacademy.s05.t01.mapper.GameMapper;
import cat.itacademy.s05.t01.dto.GameResponseDTO;
import cat.itacademy.s05.t01.repository.mongodb.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final RankingService rankingService;


    public Mono<GameResponseDTO> createGame(String playerName) {
        Game newGame = new Game(playerName);
        return gameRepository.save(newGame)
                .map(GameMapper::toResponse);
    }

    public Mono<GameResponseDTO> playHit(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Game not found")))
                .flatMap(game -> {
                    game.playerHit();
                    return gameRepository.save(game);
                }).map(GameMapper::toResponse);
    }

    public Mono<GameResponseDTO> playStand(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Game not found")))
                .flatMap(game -> {
                    game.playerStand();
                    return gameRepository.save(game);
                })
                .map(GameMapper::toResponse);
    }

    public Mono<GameResponseDTO> saveAndCheckRanking(Game game) {
        return gameRepository.save(game)
                .flatMap(savedGame -> {
                    boolean isVictory = savedGame.getState() == GameState.PLAYER_WIN
                            || savedGame.getState() == GameState.DEALER_BUST;
                    if (isVictory) {
                        return rankingService.updateRanking(savedGame.getPlayer().getName())
                                .thenReturn(GameMapper.toResponse(savedGame));
                    }
                    return Mono.just(GameMapper.toResponse(savedGame));
                });
    }

    public Mono<GameResponseDTO> deleteGame(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Game not found with ID: " + id)))
                .flatMap(game -> {
                    GameResponseDTO response = GameMapper.toResponse(game);
                    return gameRepository.delete(game)
                            .thenReturn(response);
                });
    }

}

