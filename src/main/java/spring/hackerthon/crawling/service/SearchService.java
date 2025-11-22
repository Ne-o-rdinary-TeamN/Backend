package spring.hackerthon.crawling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.hackerthon.crawling.SearchClient;
import spring.hackerthon.crawling.dto.NewsResponseDTO;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.news.domain.News;
import spring.hackerthon.news.repository.NewsRepository;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.repository.PostRepository;
import spring.hackerthon.recommendation.domain.Recommendation;
import spring.hackerthon.recommendation.repository.RecommendationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchClient searchClient;
    private final NewsRepository newsRepository;
    private final PostRepository postRepository;
    private final RecommendationRepository recommendationRepository;

    public NewsResponseDTO.NewsItemsResponseDTO searchTop5News(String query, Integer display) {
        NewsResponseDTO.NewsItemsResponseDTO newsItemsResponseDTO = searchClient.searchNews(query, display);

        //기존 데이터 삭제
        newsRepository.deleteAll();

        //서비스 내부 News 엔티티로 변환후 저장
        List<News> newsList = newsItemsResponseDTO.getItems().stream()
                .map(i -> News.builder()
                        .newsTitle(i.getTitle())
                        .url(i.getOriginallink())
                        .build())
                .collect(Collectors.toList());

        newsRepository.saveAll(newsList);
        return newsItemsResponseDTO;
    }

    public NewsResponseDTO.NewsItemsResponseDTO searchKeywordNews(Long postPk, String keywords, Integer display) {
        NewsResponseDTO.NewsItemsResponseDTO newsItemsResponseDTO = searchClient.searchNews(keywords, display);

        //게시글 찾고 없는 게시물이라면 예외처리
        Post post = postRepository.findById(postPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.POST_NOT_FOUND));

        //서비스 내부 추천 뉴스(Recommendation) 엔티티로 변환후 저장
        List<Recommendation> recommendationList = newsItemsResponseDTO.getItems().stream()
                .map(i -> {
                    Recommendation recommendation = Recommendation.builder()
                            .title(i.getTitle())
                            .url(i.getOriginallink())
                            .post(post)
                            .build();

                    //게시글에도 추천 뉴스 양방향 연관관계 매핑하기
                    post.getRecommendations().add(recommendation);

                    return recommendation;
                })
                .collect(Collectors.toList());


        recommendationRepository.saveAll(recommendationList);
        return newsItemsResponseDTO;
    }
}

