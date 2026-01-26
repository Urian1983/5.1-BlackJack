package cat.itacademy.s05.t01.service;

import cat.itacademy.s05.t01.domain.model.PlayerRanking;
import cat.itacademy.s05.t01.exceptions.GameNotFoundException;
import cat.itacademy.s05.t01.mapper.GameMapper;
import cat.itacademy.s05.t01.dto.PlayerRankingDTO;
import cat.itacademy.s05.t01.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

    @Service
    @RequiredArgsConstructor
    public class RankingService {

        private final RankingRepository rankingRepository;

        public Flux<PlayerRankingDTO> getRanking() {
            return rankingRepository.findAll()
                    .map(GameMapper::playerRankingResponse);
        }

        public Mono<PlayerRankingDTO> updateRanking(String playerName) {
            return rankingRepository.findByName(playerName)
                    .flatMap(ranking -> {
                        ranking.setGamesWon(ranking.getGamesWon() + 1);
                        return rankingRepository.save(ranking);
                    })
                    .switchIfEmpty(Mono.defer(() -> {
                        PlayerRanking newRanking = new PlayerRanking();
                        newRanking.setName(playerName);
                        newRanking.setGamesWon(1);
                        return rankingRepository.save(newRanking);
                    }))
                    .map(GameMapper::playerRankingResponse);
        }

        public Mono<PlayerRankingDTO> renamePlayer(Long playerId, String newName) {
            return rankingRepository.findById(playerId)
                    .switchIfEmpty(Mono.error(new GameNotFoundException("Player not found with ID: " + playerId)))
                    .flatMap(player -> {
                        player.setName(newName);
                        return rankingRepository.save(player);
                    })
                    .map(GameMapper::playerRankingResponse);
        }
    }


