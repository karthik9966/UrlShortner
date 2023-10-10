package com.ttu.urlShortner.service.csvServiceImplementation;

import com.ttu.urlShortner.model.CsvData;
import com.ttu.urlShortner.service.CsvService;
import com.ttu.urlShortner.utils.CsvReaderUtil;
import com.ttu.urlShortner.utils.CsvWriterUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
        List<CsvData> data;
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
    public String updateLongUrl(String longUrl, String shortUrl) throws IOException {
        List<CsvData> data = readRecords();
        List<CsvData> updatedData = new ArrayList<>();
        System.out.println(data);
        data.stream().forEach((CsvRecord) -> {
            if(CsvRecord.getShortUrl().equals(shortUrl))
            {
                CsvRecord.setLongUrl(longUrl);
            }
            updatedData.add(CsvRecord);
        });
        System.out.println(updatedData);
        CsvWriterUtil.writeDataToCsv(filePath,updatedData);
        return "Updated";
    }
}
