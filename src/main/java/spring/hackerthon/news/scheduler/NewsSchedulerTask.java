package spring.hackerthon.news.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.hackerthon.crawling.service.SearchService;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewsSchedulerTask {
    private final SearchService searchService;

    @Scheduled(cron = "0 0 * * * ?")
    public void scheduledTask() {
        log.info("Execute Scheduler - Now: {}", LocalDateTime.now());

        String query = "실시간TOP5시사";
        if(searchService.searchTop5News(query, 5) == null) {
            log.warn("failed to update news");
        }

    }
}
