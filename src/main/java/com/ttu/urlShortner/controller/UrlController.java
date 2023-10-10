package com.ttu.urlShortner.controller;

import com.ttu.urlShortner.service.UrlService;
import com.ttu.urlShortner.utils.Url;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService)
    {
        this.urlService = urlService;
    }

    @PostMapping("/longUrl")
    @ResponseBody
    public String getShortUrl(@RequestBody Url longUrl)
    {
        return urlService.getShortUrl(longUrl);
    }

    @GetMapping("/shortUrl")
    public ResponseEntity<String> redirectTOLongUrl(@RequestBody Url shortUrl)
    {
        String longUrl = urlService.redirectToLongUrl(shortUrl);
        String path = "redirect:/" + longUrl;
        return new ResponseEntity<>(path, HttpStatus.valueOf(302));
    }
}
