package com.ttu.urlShortner.Exception;

public class JsonParsingException extends RuntimeException {

    private String message;

    public JsonParsingException(String message) {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
