package cat.itacademy.s05.t01.service;

import cat.itacademy.s05.t01.domain.sql.PlayerRanking;
import cat.itacademy.s05.t01.dto.GameMapper;
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
                        return rankingRepository.save(new PlayerRanking(null, playerName, 1));
                    }))
                    .map(GameMapper::playerRankingResponse);
        }



    }


