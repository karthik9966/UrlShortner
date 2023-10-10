package com.ttu.urlShortner.service.urlServiceImplementation;

import com.ttu.urlShortner.model.CsvData;
import com.ttu.urlShortner.service.CsvService;
import com.ttu.urlShortner.service.UrlService;
import com.ttu.urlShortner.service.csvServiceImplementation.CommonsCsvImpl;
import com.ttu.urlShortner.utils.HashGenerator;
import com.ttu.urlShortner.utils.Url;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class CsvImpl implements UrlService {

    private final HashGenerator hashGenerator;
    private final CsvService csvService;

    public CsvImpl(HashGenerator hashGenerator, CommonsCsvImpl csvService)
    {
        this.hashGenerator = hashGenerator;
        this.csvService = csvService;
    }

    @Override
    public String getShortUrl(Url longUrl) {
        String shortUrl;
        try {
            shortUrl =  hashGenerator.hash(longUrl.getUrl(),"SHA-1",8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Couldn't generate short URL");
        }
        CsvData csvData = new CsvData();
        csvData.setShortUrl(shortUrl);
        csvData.setLongUrl(longUrl.getUrl());
        csvData.setExpiry("0");
        csvService.writeRecord(csvData);
        return shortUrl;
    }

    @Override
    public String redirectToLongUrl(Url shortUrl) {
        return csvService.getLongUrl(shortUrl);
    }
}
