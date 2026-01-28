package it.academy.sprint5.task1.blackjack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IlegalMoveException extends RuntimeException {
    public IlegalMoveException(String message) {
        super(message);
    }

}
