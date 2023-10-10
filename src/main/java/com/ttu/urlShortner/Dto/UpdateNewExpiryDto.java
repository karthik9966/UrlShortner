package com.ttu.urlShortner.Dto;

public class UpdateNewExpiryDto {

    private String newExpiry;
    private String shortUrl;

    public String getNewExpiry() {
        return newExpiry;
    }

    public void setNewExpiry(String newExpiry) {
        this.newExpiry = newExpiry;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
