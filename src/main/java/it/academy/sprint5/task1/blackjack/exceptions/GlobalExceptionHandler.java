package it.academy.sprint5.task1.blackjack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IlegalMoveException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleIllegalMove(IlegalMoveException ex) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", ex.getMessage()))
        );
    }

    @ExceptionHandler(GameNotFoundException.class)
    public Mono<ResponseEntity<Map<String, String>>> HandleGameNotFound(GameNotFoundException ex) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", ex.getMessage()))
        );
    }

}
