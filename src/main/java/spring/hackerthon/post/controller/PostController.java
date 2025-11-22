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

import java.util.List;

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

  
    @GetMapping("/{userPk}/join")
    @Operation(summary = "본인이 참여한 투표글 조회 API", description = "본인이 참여한 투표글들을 조회하는 API입니다.")
    public ApiResponse<PostResponseDTO.TotalPostViewResultDTO> viewParticipatePost(
            @AuthenticationPrincipal JwtPrincipal user,
            @PathVariable("userPk") Long userPk) {

        List<PostResponseDTO.SinglePostViewResultDTO> participatePosts = postService.getParticipatePost(userPk);
        return ApiResponse.onSuccess(PostConverter.toTotalPostViewResultDTO(participatePosts));
    }

    @GetMapping("/my")
    @Operation(summary = "본인이 생성한 투표글 조회 API", description = "본인이 생성한 투표글들을 조회하는 API입니다.")
    public ApiResponse<PostResponseDTO.TotalPostViewResultDTO> viewMyPost(
            @AuthenticationPrincipal JwtPrincipal user) {

        List<PostResponseDTO.SinglePostViewResultDTO> myPosts = postService.getMyPost(user.userPk());
        return ApiResponse.onSuccess(PostConverter.toTotalPostViewResultDTO(myPosts));
    }

    @PostMapping("/vote")
    @Operation(summary = "투표", description = "투표")
    public ApiResponse<Boolean> vote(@AuthenticationPrincipal JwtPrincipal user, VoteReq req) {
        return ApiResponse.onSuccess(postService.vote(user, req));
    }
}
