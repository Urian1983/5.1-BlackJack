package cat.itacademy.s05.t01.controller;

import cat.itacademy.s05.t01.dto.PlayerRankingDTO;
import cat.itacademy.s05.t01.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public Flux<PlayerRankingDTO> getRanking() {
        return rankingService.getRanking();
    }

    @PutMapping("/player/{playerId}")
    public Mono<PlayerRankingDTO> updatePlayer(@PathVariable Long playerId, @RequestBody String newName) {
        return rankingService.renamePlayer(playerId, newName);
    }

}
