package com.ttu.urlShortner.service;

import com.ttu.urlShortner.model.CsvData;
import com.ttu.urlShortner.utils.Url;

import java.util.List;

public interface CsvService {
    public void writeRecord(CsvData csvData);
    public List<CsvData> readRecord();
    public void deleteRecord(Url url);
    public String getLongUrl(Url url);
}