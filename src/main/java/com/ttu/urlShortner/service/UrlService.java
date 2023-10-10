package com.ttu.urlShortner.service;

import java.io.IOException;

public interface UrlService {

    public String createShortUrl(String longUrl);

    public String redirectToLongUrl(String shortUrl);

    public String updateLongUrl(String longUrl, String shortUrl) throws IOException;
}
