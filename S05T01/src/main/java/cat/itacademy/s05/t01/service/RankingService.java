package cat.itacademy.s05.t01.service;

import cat.itacademy.s05.t01.dto.GameMapper;
import cat.itacademy.s05.t01.dto.PlayerRankingDTO;
import cat.itacademy.s05.t01.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RankingService {
    private final RankingRepository rankingRepository;

    public Flux<PlayerRankingDTO> getGlobalRanking() {
        return rankingRepository.findAll()
                .map(GameMapper::playerRankingResponse);

    }


}
