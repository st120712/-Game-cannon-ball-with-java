package com.nhnacademy.game.exception;

public class AlreadyExistException extends NullPointerException {
    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String msg) {
        super(msg);
    }
}
