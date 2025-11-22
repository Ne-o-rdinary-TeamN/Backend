package spring.hackerthon.news.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record HotNewsRes(
        List<HotNews> newsList
) {
}
