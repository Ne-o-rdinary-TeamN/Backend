package spring.hackerthon.news.dto;

import lombok.Builder;

@Builder
public record HotNews(
        long newsPk,
        String title,
        String url
) {
}
