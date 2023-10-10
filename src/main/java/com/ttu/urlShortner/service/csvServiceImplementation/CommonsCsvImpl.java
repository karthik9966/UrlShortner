package com.ttu.urlShortner.service.csvServiceImplementation;

import com.ttu.urlShortner.model.CsvData;
import com.ttu.urlShortner.service.CsvService;
import com.ttu.urlShortner.utils.CsvReaderUtil;
import com.ttu.urlShortner.utils.CsvWriterUtil;
import com.ttu.urlShortner.utils.Url;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CommonsCsvImpl implements CsvService {

    private String filePath = "C:\\Users\\navak\\Intellij\\urlShortner\\src\\main\\resources\\ta.csv";

    public void writeRecord(CsvData csvData)
    {
        try {
            CsvWriterUtil.writeDataToCsv(filePath,csvData);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to file");
        }
    }

    @Override
    public List<CsvData> readRecord() {
        List<CsvData> data;
        try {
            data=CsvReaderUtil.readCsvData(filePath);
        } catch (IOException e) {
            throw new RuntimeException("No CSV File found");
        }
        return data;
    }

    public String getLongUrl(Url url)
    {
        List<CsvData> data;
        try {
             data=CsvReaderUtil.readCsvData(filePath);
        } catch (IOException e) {
            throw new RuntimeException("No CSV File found");
        }
        CsvData csvData = data.stream().filter((csvRecord) -> csvRecord.getShortUrl().equals(url.getUrl())).
                findFirst().orElseThrow(() -> new RuntimeException("No record found for the given URL"));
        return csvData.getLongUrl();
    }
}
