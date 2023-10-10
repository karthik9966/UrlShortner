package com.ttu.urlShortner.Dto;

public class UpdateShortUrl {

    private String shortUrl;
    private String newDestinationUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getNewDestinationUrl() {
        return newDestinationUrl;
    }

    public void setNewDestinationUrl(String newDestinationUrl) {
        this.newDestinationUrl = newDestinationUrl;
    }
}
