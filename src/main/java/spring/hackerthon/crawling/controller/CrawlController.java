package spring.hackerthon.crawling.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "실시간 Top5 시사 뉴스 검색 API", description = "실시간 Top5 시사 뉴스를 크롤링 해오는 API 입니다.")
    public ApiResponse<NewsResponseDTO.NewsItemsResponseDTO> searchNews(@RequestParam(defaultValue = "5") Integer display) {
        String query = "실시간TOP5시사";
        NewsResponseDTO.NewsItemsResponseDTO newsItemsResponseDTO = searchService.searchTop5News(query, display);
        return ApiResponse.onSuccess(newsItemsResponseDTO);
    }

    @GetMapping("/keyword/{postPk}")
    @Operation(summary = "Keyword 뉴스 검색 API", description = "키워드로 뉴스를 크롤링 해오는 API 입니다.")
    public ApiResponse<NewsResponseDTO.NewsItemsResponseDTO> searchKeyword(
                                       @PathVariable("postPk") Long postPk,
                                       @RequestParam String keywordQuery,
                                       @RequestParam(defaultValue = "3") Integer display) {
        NewsResponseDTO.NewsItemsResponseDTO newsItemsResponseDTO = searchService.searchKeywordNews(postPk, keywordQuery, display);
        return ApiResponse.onSuccess(newsItemsResponseDTO);
    }
}
