package cat.itacademy.s05.t01.controller;

import cat.itacademy.s05.t01.dto.GameResponseDTO;
import cat.itacademy.s05.t01.service.GameService;
import cat.itacademy.s05.t01.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/new/")
    @ResponseStatus(HttpStatus.CREATED) //201
    public Mono<GameResponseDTO> createGame(@RequestBody String playerName){
        return gameService.createGame(playerName);
    }

    @PostMapping("/{id}/hit")
    public Mono<GameResponseDTO> playHit (@PathVariable String id) {
        return gameService.playHit(id);
    }

    @PostMapping("/{id}/stand")
    public Mono<GameResponseDTO> playStand (@PathVariable String id){
        return gameService.playStand(id);
    }

    @DeleteMapping("/{id}/delete")
    public Mono<GameResponseDTO> deleteGame(@PathVariable String id) {
        return gameService.deleteGame(id);
    }

}
