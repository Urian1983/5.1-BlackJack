package it.academy.sprint5.task1.blackjack.service;

import it.academy.sprint5.task1.blackjack.domain.Game;
import it.academy.sprint5.task1.blackjack.domain.enums.GameState;
import it.academy.sprint5.task1.blackjack.dto.GameResponseDTO;
import it.academy.sprint5.task1.blackjack.exceptions.GameNotFoundException;
import it.academy.sprint5.task1.blackjack.mapper.GameMapper;
import it.academy.sprint5.task1.blackjack.repository.mongodb.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final RankingService rankingService;
    private final GameMapper gameMapper;

    public Mono<GameResponseDTO> createGame(String playerName) {
        Game newGame = new Game(playerName);
        return gameRepository.save(newGame)
                .map(gameMapper::toDTO);
    }
    public Mono<GameResponseDTO> playerHit(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Game not found with ID: " + id)))
                .flatMap(game -> {
                    game.playerHit(); // Lógica de dominio del Blackjack
                    return handleGameEnd(game);
                });
    }

    public Mono<GameResponseDTO> playerStand(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Game not found with ID: " + id)))
                .flatMap(game -> {
                    game.playerStand(); // Lógica de dominio del Dealer
                    return handleGameEnd(game);
                });
    }

    public Mono<Void> deleteGame(String id) {
        return gameRepository.existsById(id)
                .flatMap(exists -> exists
                        ? gameRepository.deleteById(id)
                        : Mono.error(new GameNotFoundException("Cannot delete. Game not found: " + id)));
    }

    private Mono<GameResponseDTO> handleGameEnd(Game game) {
        Mono<Game> savedGame = gameRepository.save(game);
        if (game.getState() == GameState.PLAYER_WIN || game.getState() == GameState.DEALER_BUST) {
            return rankingService.updateRanking(game.getPlayer().getName())
                    .then(savedGame)
                    .map(gameMapper::toDTO);
        }
        return savedGame.map(gameMapper::toDTO);
    }


    }

