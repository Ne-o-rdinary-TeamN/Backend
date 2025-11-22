package spring.hackerthon.crawling.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.hackerthon.crawling.dto.NewsResponseDTO;
import spring.hackerthon.crawling.service.SearchService;
import spring.hackerthon.global.response.ApiResponse;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class CrawlController {

    private final SearchService searchService;

    @GetMapping("/news")
    public ApiResponse<NewsResponseDTO.NewsItemsResponseDTO> searchNews(@RequestParam String query,
                                                                        @RequestParam(defaultValue = "10") Integer display) {
        NewsResponseDTO.NewsItemsResponseDTO newsItemsResponseDTO = searchService.searchNews(query, display);
        return ApiResponse.onSuccess(newsItemsResponseDTO);
    }
}
