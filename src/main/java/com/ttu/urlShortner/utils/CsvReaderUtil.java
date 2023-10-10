package com.ttu.urlShortner.utils;
import com.ttu.urlShortner.model.CsvData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderUtil {
    public static List<CsvData> readCsvData(String filePath) throws IOException {
        List<CsvData> dataList = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String shortUrl = csvRecord.get(0);
                String longUrl = csvRecord.get(1);
                String expiry = csvRecord.get(2);

                CsvData csvData = new CsvData();
                csvData.setShortUrl(shortUrl);
                csvData.setLongUrl(longUrl);
                csvData.setExpiry(expiry);
                dataList.add(csvData);
            }
        }

        return dataList;
    }
}

