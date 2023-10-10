package com.ttu.urlShortner.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ttu.urlShortner.Dto.UpdateShortUrl;
import com.ttu.urlShortner.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping()
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

    @PatchMapping("/update")
    public void updateLongUrl(@RequestBody UpdateShortUrl request) throws IOException {
        urlService.updateLongUrl(request.getNewDestinationUrl(),request.getShortUrl());
    }

}
