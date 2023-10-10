package com.ttu.urlShortner.service;

import com.ttu.urlShortner.model.CsvData;

import java.io.IOException;
import java.util.List;

public interface CsvService {
    public void writeRecord(List<CsvData> csvDataList);
    public List<CsvData> readRecords();
    public void deleteRecords(List<String> shortUrls);
    public String getLongUrl(String shortUrl);
    public String updateLongUrl(String longUrl, String shortUrl) throws IOException;
}