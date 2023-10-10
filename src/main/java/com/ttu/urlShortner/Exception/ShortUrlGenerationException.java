package com.ttu.urlShortner.Exception;

public class ShortUrlGenerationException extends RuntimeException {
    private String message;
    public ShortUrlGenerationException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
