package com.nhnacademy.game.exception;

import java.util.Objects;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String msg) {
        super(Objects.isNull(msg) ? "" : msg);
    }
}
