package com.ttu.urlShortner.service;

import com.ttu.urlShortner.utils.Url;

public interface UrlService {

    public String getShortUrl(Url longUrl);

    public String redirectToLongUrl(Url shortUrl);
}
