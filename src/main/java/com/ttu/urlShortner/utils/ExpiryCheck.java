package com.ttu.urlShortner.utils;

import com.ttu.urlShortner.model.CsvData;
import com.ttu.urlShortner.service.CsvService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpiryCheck {

    private final CsvService csvService;

    public ExpiryCheck(CsvService csvService) {
        this.csvService = csvService;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void runDaily()
    {
        List<CsvData> data = csvService.readRecords();

        List<String> expiredShortUrls = data.stream().filter((csvRecord) -> (new Date().getTime() - csvRecord.getExpiry().getTime()) / 24 * 60 * 60 * 1000 >= 180)
                .map((csvRecord) -> csvRecord.getShortUrl())
                .collect(Collectors.toList());

        csvService.deleteRecords(expiredShortUrls);

    }

}
