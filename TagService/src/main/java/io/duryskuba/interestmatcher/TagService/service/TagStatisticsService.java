package io.duryskuba.interestmatcher.TagService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TagStatisticsService {


    @Async
    @Scheduled(cron = "0 59 * ? * *")
    public void generateStatistics() {

        LocalDateTime now = LocalDateTime.now();
        final LocalDateTime dateFrom = now.minusHours(1);
        final LocalDateTime dateTo = now;

//        this.tagDetailsDao.findTagDetailsWhereDateBetween(dateFrom,dateTo)
//                .entrySet()
//                .forEach(t -> {
//                    System.out.println(t.getKey() + " " + t.getValue());
//                    tagStatisticsDao.create(
//                            new TagStatistics(t.getKey(),t.getValue(),dateFrom,dateTo));
//                });

    }
}
