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
    public ResponseEntity<String> createShortUrl(@RequestAttribute String body)
    {
        String shortUrl = urlService.createShortUrl(body);
        return new ResponseEntity<>(shortUrl,HttpStatus.CREATED);
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
    public ResponseEntity updateLongUrl(@RequestAttribute UpdateLongUrlDto body) {
        urlService.updateLongUrl(body.getNewDestination(),body.getShortUrl());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/updateExpiry")
    public ResponseEntity updateExpiry(@RequestAttribute UpdateNewExpiryDto body) {
        urlService.updateExpiry(body.getShortUrl(),body.getNewExpiry());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
