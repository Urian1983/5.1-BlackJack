package cat.itacademy.s05.t01.exceptions;

public class IlegalMoveException extends RuntimeException {
    public IlegalMoveException(String message) {
        super(message);
    }
}
