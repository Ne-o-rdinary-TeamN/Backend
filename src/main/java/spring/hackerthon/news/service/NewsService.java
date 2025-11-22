package spring.hackerthon.news.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.hackerthon.news.domain.News;
import spring.hackerthon.news.dto.HotNews;
import spring.hackerthon.news.dto.HotNewsRes;
import spring.hackerthon.news.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {
    private final NewsRepository newsRepository;

    public HotNewsRes getHotNews() {
        List<News> news = newsRepository.findTop5ByOrderByNewsPkDesc();
        List<HotNews> hotNews = new ArrayList<>();

        for(News n : news){
            hotNews.add(HotNews.builder().newsPk(n.getNewsPk()).title(n.getNewsTitle()).url(n.getUrl()).build());
        }

        return HotNewsRes.builder().newsList(hotNews).build();
    }
}
