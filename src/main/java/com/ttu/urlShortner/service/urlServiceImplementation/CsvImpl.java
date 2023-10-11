package com.ttu.urlShortner.service.urlServiceImplementation;

import com.ttu.urlShortner.Exception.ShortUrlGenerationException;
import com.ttu.urlShortner.model.CsvData;
import com.ttu.urlShortner.service.CsvService;
import com.ttu.urlShortner.service.UrlService;
import com.ttu.urlShortner.service.csvServiceImplementation.CommonsCsvImpl;
import com.ttu.urlShortner.utils.HashGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class CsvImpl implements UrlService {

    private final HashGenerator hashGenerator;
    private final CsvService csvService;

    @Value("${app.url.default}")
    private String defaultUrl;

    @Value("${app.hashing.algorithm}")
    private String hashingAlgorithm;

    public CsvImpl(HashGenerator hashGenerator, CommonsCsvImpl csvService)
    {
        this.hashGenerator = hashGenerator;
        this.csvService = csvService;
    }

    @Override
    public String createShortUrl(String longUrl) {
        String shortUrl;
        try {
            shortUrl = hashGenerator.hash(longUrl,hashingAlgorithm,8);
        } catch (NoSuchAlgorithmException e) {
            throw new ShortUrlGenerationException("Couldn't generate short URL as there is no algorithm "+hashingAlgorithm);
        }
        CsvData csvData = new CsvData();
        csvData.setShortUrl(shortUrl);
        csvData.setLongUrl(longUrl);
        csvData.setExpiry(new Date());
        List<CsvData> data = csvService.readRecords();
        data.add(csvData);
        csvService.writeRecord(data);
        return shortUrl;
    }

    public String redirectToLongUrl(String shortUrl) {
        return csvService.getLongUrl(defaultUrl+shortUrl);
    }

    @Override
    public void updateLongUrl(String longUrl, String shortUrl) {
        csvService.updateLongUrl(longUrl,shortUrl);
    }

    @Override
    public void updateExpiry(String shortUrl, String newExpiry) {
        csvService.updateNewExpiry(shortUrl,newExpiry);
    }
}
