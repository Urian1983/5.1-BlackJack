package it.academy.sprint5.task1.blackjack.service;

import it.academy.sprint5.task1.blackjack.domain.model.PlayerRanking;
import it.academy.sprint5.task1.blackjack.dto.PlayerRankingDTO;
import it.academy.sprint5.task1.blackjack.exceptions.GameNotFoundException;
import it.academy.sprint5.task1.blackjack.mapper.GameMapper;
import it.academy.sprint5.task1.blackjack.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;
    private final GameMapper gameMapper;

    public Flux<PlayerRankingDTO> getRanking() {
        return rankingRepository.findAll()
                .map(gameMapper::playerRankingResponse);
    }

    public Mono<PlayerRankingDTO> updateRanking(String playerName) {
        return rankingRepository.findByName(playerName)
                // Usamos flatMap para la lógica de guardado (I/O)
                .flatMap(ranking -> {
                    ranking.setGamesWon(ranking.getGamesWon() + 1);
                    return rankingRepository.save(ranking);
                })
                // Si no existe, creamos y guardamos
                .switchIfEmpty(Mono.defer(() -> {
                    PlayerRanking newRanking = new PlayerRanking();
                    newRanking.setName(playerName);
                    newRanking.setGamesWon(1);
                    return rankingRepository.save(newRanking);
                }))
                // AQUÍ ESTÁ LA CONCORDANCIA:
                // Todo lo anterior devuelve un Mono<PlayerRanking>.
                // Ahora el map recibe un PlayerRanking y devuelve un DTO.
                .map(gameMapper::playerRankingResponse);
    }

    public Mono<PlayerRankingDTO> renamePlayer(Long playerId, String newName) {
        return rankingRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new GameNotFoundException("Player not found with ID: " + playerId)))
                .flatMap(player -> {
                    player.setName(newName);
                    return rankingRepository.save(player);
                })
                .map(gameMapper::playerRankingResponse);
    }


}
