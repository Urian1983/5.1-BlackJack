package cat.itacademy.s05.t01.controller;

import cat.itacademy.s05.t01.dto.GameResponseDTO;
import cat.itacademy.s05.t01.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@Tag(name = "Game", description = "API for managing a Blackjack game")
public class GameController {

    private final GameService gameService;

    @PostMapping("/new/")
    @ResponseStatus(HttpStatus.CREATED) //201
    @Operation(summary = "Creates a new play game")
    @ApiResponse(
            responseCode = "201",
            description = "New gameplay created",
            content = @Content(schema = @Schema(implementation = GameResponseDTO.class))
    )
    public Mono<GameResponseDTO> createGame(@RequestBody String playerName){
        return gameService.createGame(playerName);
    }

    @PostMapping("/{id}/hit")
    @Operation(summary = "To get card for scoring")
    @ApiResponse(
            responseCode = "200",
            description = "To stand and give turn to the computer/dealer",
            content = @Content(schema = @Schema(implementation = GameResponseDTO.class))
    )
    public Mono<GameResponseDTO> playHit (@PathVariable String id) {
        return gameService.playHit(id);
    }

    @PostMapping("/{id}/stand")
    @Operation(summary = "To stand and give turn to the computer/dealer")
    @ApiResponse(
            responseCode = "200",
            description = "To stand and give turn to the computer/dealer",
            content = @Content(schema = @Schema(implementation = GameResponseDTO.class))
    )
    public Mono<GameResponseDTO> playStand (@PathVariable String id){
        return gameService.playStand(id);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete gameplay")
    @ApiResponse(
            responseCode = "200",
            description = "Delete gameplay",
            content = @Content(schema = @Schema(implementation = GameResponseDTO.class))
    )
    public Mono<GameResponseDTO> deleteGame(@PathVariable String id) {
        return gameService.deleteGame(id);
    }

}
