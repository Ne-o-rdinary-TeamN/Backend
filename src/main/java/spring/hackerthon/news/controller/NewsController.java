package spring.hackerthon.news.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.hackerthon.news.dto.HotNewsRes;
import spring.hackerthon.news.service.NewsService;

@Tag(name = "뉴스 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/hot")
    @Operation(summary = "핫 뉴스 전체 목록 조회", description = "핫 뉴스 전체 목록 조회(5개)")
    public HotNewsRes getHotNews() {
        return newsService.getHotNews();
    }
}
