package spring.hackerthon.crawling.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.hackerthon.crawling.dto.NewsResponseDTO;
import spring.hackerthon.crawling.service.SearchService;
import spring.hackerthon.global.response.ApiResponse;

@Tag(name = "크롤링 컨트롤러")
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class CrawlController {

    private final SearchService searchService;

    @GetMapping("/news")
    @Operation(summary = "실시간 Top5 시사 뉴스 검색", description = "실시간 Top5 시사 뉴스를 크롤링 해오는 API 입니다.")
    public ApiResponse<NewsResponseDTO.NewsItemsResponseDTO> searchNews(@RequestParam(defaultValue = "5") Integer display) {
        String query = "실시간TOP5시사";
        NewsResponseDTO.NewsItemsResponseDTO newsItemsResponseDTO = searchService.searchTop5News(query, display);
        return ApiResponse.onSuccess(newsItemsResponseDTO);
    }
}
