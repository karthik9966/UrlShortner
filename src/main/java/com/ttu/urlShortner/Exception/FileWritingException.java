package com.ttu.urlShortner.Exception;

public class FileWritingException extends RuntimeException {

    private String message;
    public FileWritingException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
