package spring.hackerthon.crawling.service;

import org.springframework.stereotype.Service;
import spring.hackerthon.crawling.SearchClient;
import spring.hackerthon.crawling.dto.NewsResponseDTO;

@Service
public class CrawlService {

    @Service
    public class SearchService {

        private final SearchClient searchClient;

        public SearchService(SearchClient searchClient) {
            this.searchClient = searchClient;
        }

        public NewsResponseDTO.NewsItemsResponseDTO searchNews(String query, Integer display) {
            return searchClient.searchNews(query, display);
        }
    }

}
