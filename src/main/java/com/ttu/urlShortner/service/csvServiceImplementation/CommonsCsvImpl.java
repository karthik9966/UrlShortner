package com.ttu.urlShortner.service.csvServiceImplementation;

import com.ttu.urlShortner.model.CsvData;
import com.ttu.urlShortner.service.CsvService;
import com.ttu.urlShortner.utils.CsvReaderUtil;
import com.ttu.urlShortner.utils.CsvWriterUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonsCsvImpl implements CsvService {

    private String filePath = "C:\\Users\\navak\\Intellij\\urlShortner\\src\\main\\resources\\ta.csv";

    @Override
    public void writeRecord(List<CsvData> csvDataList)
    {
        try {
            CsvWriterUtil.writeDataToCsv(filePath,csvDataList);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to file");
        }
    }

    @Override
    public List<CsvData> readRecords() {
        List<CsvData> data = new ArrayList<>();
        try {
            data=CsvReaderUtil.readCsvData(filePath);
        } catch (IOException e) {
            throw new RuntimeException("No CSV File found");
        }
        return data;
    }

    @Override
    public void deleteRecords(List<String> shortUrls) {
        shortUrls.stream().forEach((shortUrl) -> {
            List<CsvData> data = readRecords();
            List<CsvData> updatedData = data.stream().filter((csvRecord) -> !csvRecord.getShortUrl().equals(shortUrl))
                    .collect(Collectors.toList());
            try {
                CsvWriterUtil.writeDataToCsv(filePath,updatedData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public String getLongUrl(String url)
    {
        List<CsvData> data = readRecords();
        CsvData csvData = data.stream().filter((csvRecord) -> csvRecord.getShortUrl().equals(url)).
                findFirst().orElseThrow(() -> new RuntimeException("No record found for the given URL"));
        return csvData.getLongUrl();
    }

    @Override
    public void updateLongUrl(String longUrl, String shortUrl){
        List<CsvData> data = readRecords();
        List<CsvData> updatedData = new ArrayList<>();
        data.stream().forEach((CsvRecord) -> {
            if(CsvRecord.getShortUrl().equals(shortUrl))
            {
                CsvRecord.setLongUrl(longUrl);
            }
            updatedData.add(CsvRecord);
        });
        try {
            CsvWriterUtil.writeDataToCsv(filePath,updatedData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateNewExpiry(String shortUrl, String newExpiry) {
        List<CsvData> data = readRecords();
        List<CsvData> updatedData = new ArrayList<>();
        data.stream().forEach((CsvRecord) -> {
            if(CsvRecord.getShortUrl().equals(shortUrl))
            {
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date date;
                try {
                     date = formatter.parse(newExpiry);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                CsvRecord.setExpiry(date);
            }
            updatedData.add(CsvRecord);
        });
        try {
            CsvWriterUtil.writeDataToCsv(filePath,updatedData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
