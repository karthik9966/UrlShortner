package com.ttu.urlShortner.service;

public interface UrlService {

    public String createShortUrl(String longUrl);

    public String redirectToLongUrl(String shortUrl);

    public void updateLongUrl(String longUrl, String shortUrl);

    void updateExpiry(String shortUrl, String newExpiry);
}
