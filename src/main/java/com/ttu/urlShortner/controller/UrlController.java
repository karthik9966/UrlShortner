package com.ttu.urlShortner.controller;

import com.ttu.urlShortner.Dto.UpdateNewExpiryDto;
import com.ttu.urlShortner.Dto.UpdateLongUrlDto;
import com.ttu.urlShortner.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService)
    {
        this.urlService = urlService;
    }

    @PostMapping("/longUrl")
    @ResponseBody
    public String createShortUrl(@RequestBody String longUrl)
    {
        return urlService.createShortUrl(longUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirectTOLongUrl(@PathVariable String shortUrl, HttpServletResponse response)
    {
        String longUrl = urlService.redirectToLongUrl(shortUrl);
        response.setHeader("Location", longUrl);
        String path = "redirect:/" + longUrl;
        return new ResponseEntity<>(path, HttpStatus.valueOf(302));
    }

    @PatchMapping("/updateLongUrl")
    public void updateLongUrl(@RequestBody UpdateLongUrlDto request) {
        urlService.updateLongUrl(request.getNewDestination(),request.getShortUrl());
    }

    // New Expiry has to be passed in "EEE MMM dd HH:mm:ss zzz yyyy" format
    @PatchMapping("/updateExpiry")
    public void updateExpiry(@RequestBody UpdateNewExpiryDto newExpiry) {
        urlService.updateExpiry(newExpiry.getShortUrl(),newExpiry.getNewExpiry());
    }

}
