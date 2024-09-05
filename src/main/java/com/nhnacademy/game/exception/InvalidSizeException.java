package com.nhnacademy.game.exception;

import java.util.Objects;

public class InvalidSizeException extends RuntimeException {
    public InvalidSizeException(String msg) {
        super(Objects.isNull(msg) ? "" : msg);
    }
}
