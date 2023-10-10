package com.ttu.urlShortner.Exception;

public class NoSuchRecordException extends RuntimeException {
    private String message;

    public NoSuchRecordException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
