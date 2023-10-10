package com.ttu.urlShortner.utils;

import com.ttu.urlShortner.Exception.FileWritingException;
import com.ttu.urlShortner.model.CsvData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriterUtil {
    public static void writeDataToCsv(String filePath, List<CsvData> csvData) throws IOException {
        File csvFile = new File(filePath);
        if (csvFile.exists()) {
            FileUtils.forceDelete(csvFile);
        }
        csvData.stream().forEach((csvRecord) -> {
            try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath,true), CSVFormat.DEFAULT)) {
                csvPrinter.printRecord(csvRecord.getShortUrl(), csvRecord.getLongUrl(), csvRecord.getExpiry());
            } catch (IOException e) {
                throw new FileWritingException("Couldn't write to a file");
            }
        });
    }
}


