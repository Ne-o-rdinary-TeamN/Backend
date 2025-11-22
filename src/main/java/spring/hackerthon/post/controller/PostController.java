package spring.hackerthon.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.hackerthon.crawling.dto.NewsResponseDTO;
import spring.hackerthon.crawling.service.SearchService;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.security.JwtPrincipal;
import spring.hackerthon.post.converter.PostConverter;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostRequestDTO;
import spring.hackerthon.post.dto.PostResponseDTO;
import spring.hackerthon.post.dto.VoteReq;
import spring.hackerthon.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final SearchService searchService;

    @PostMapping("/create")
    @Operation(summary = "투표글 생성", description = "투표글 생성과 동시에 관련 뉴스 기사 3개를 크롤링합니다.")
    public ApiResponse<PostResponseDTO.PostCreateResponseDTO> createPost(
            @AuthenticationPrincipal JwtPrincipal user,
            @RequestBody @Valid PostRequestDTO.PostCreateRequestDTO request) {

        Post post = postService.joinPost(user.userPk(), request);
        String keywords = postService.getKeywords(post);
        searchService.searchKeywordNews(post.getPostPk(), keywords, 3);

        return ApiResponse.onSuccess(PostConverter.toPostCreateResponseDTO(post));
    }

    @PostMapping("/vote")
    @Operation(summary = "투표", description = "투표")
    public ApiResponse<Boolean> vote(@AuthenticationPrincipal JwtPrincipal user, VoteReq req) {
        return ApiResponse.onSuccess(postService.vote(user, req));
    }
}
