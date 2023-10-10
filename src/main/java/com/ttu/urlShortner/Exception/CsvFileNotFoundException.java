package com.ttu.urlShortner.Exception;

public class CsvFileNotFoundException extends RuntimeException{
    private String message;
    public CsvFileNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
