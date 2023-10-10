package com.ttu.urlShortner.utils;

import com.ttu.urlShortner.model.CsvData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriterUtil {
    public static void writeDataToCsv(String filePath, List<CsvData> csvData) throws IOException {
        csvData.stream().forEach((csvRecord) -> {
            try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT)) {
                csvPrinter.printRecord(csvRecord.getShortUrl(), csvRecord.getLongUrl(), csvRecord.getExpiry());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}


