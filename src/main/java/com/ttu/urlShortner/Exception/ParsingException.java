package com.ttu.urlShortner.Exception;

public class ParsingException extends RuntimeException {

    private String message;

    public ParsingException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
