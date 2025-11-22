package spring.hackerthon.crawling.dto;

import lombok.Data;

import java.util.List;

public class NewsResponseDTO {

    @Data
    public static class NewsItemsResponseDTO {
        private String lastBuildDate;
        private int total;
        private int start;
        private int display;
        private List<SingleNewsItemDTO> items;
    }

    @Data
    public static class SingleNewsItemDTO {
        private String title;
        private String originallink;
        private String link;
        private String description;
        private String pubDate;
    }

    @Data
    public static class SaveNewsItemDTO {
        private String title;
        private String url;
    }
}
