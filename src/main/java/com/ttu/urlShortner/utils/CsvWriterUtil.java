package com.ttu.urlShortner.utils;

import com.ttu.urlShortner.model.CsvData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriterUtil {
    public static void writeDataToCsv(String filePath, CsvData csvData) throws IOException {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(csvData.getShortUrl(), csvData.getLongUrl(), csvData.getExpiry());
        }
    }
}


