package com.ttu.urlShortner.utils;
import com.ttu.urlShortner.Exception.ParsingException;
import com.ttu.urlShortner.model.CsvData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvReaderUtil {

    @Value("${app.date.format}")
    private static String dateFormat;

    public static List<CsvData> readCsvData(String filePath) throws IOException {
        List<CsvData> dataList = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String shortUrl = csvRecord.get(0);
                String longUrl = csvRecord.get(1);
                String expiry = csvRecord.get(2);
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date date = formatter.parse(expiry);

                CsvData csvData = new CsvData();
                csvData.setShortUrl(shortUrl);
                csvData.setLongUrl(longUrl);
                csvData.setExpiry(date);
                dataList.add(csvData);
            }
        } catch (ParseException e) {
            throw new ParsingException("Format Error. Pelase pass date in "+dateFormat);
        }

        return dataList;
    }
}

