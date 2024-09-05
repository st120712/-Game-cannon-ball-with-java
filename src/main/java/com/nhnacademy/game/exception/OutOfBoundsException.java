package com.nhnacademy.game.exception;

import java.util.Objects;

public class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException(String msg) {
        super(Objects.isNull(msg) ? "" : msg);
    }
}
