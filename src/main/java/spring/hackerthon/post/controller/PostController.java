package spring.hackerthon.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.hackerthon.crawling.service.SearchService;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.security.JwtPrincipal;
import spring.hackerthon.post.converter.PostConverter;
import spring.hackerthon.post.domain.Category;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostRequestDTO;
import spring.hackerthon.post.dto.PostResponseDTO;
import spring.hackerthon.post.dto.VoteReq;
import spring.hackerthon.post.service.PostService;

import java.util.List;

@Tag(name = "투표글 컨트롤러")
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
    @Operation(summary = "본인이 참여한 투표글 조회", description = "본인이 참여한 투표글들을 조회하는 API입니다.")
    public ApiResponse<PostResponseDTO.TotalPostViewResultDTO> viewParticipatePost(
            @AuthenticationPrincipal JwtPrincipal user,
            @PathVariable("userPk") Long userPk) {

        List<PostResponseDTO.SinglePostViewResultDTO> participatePosts = postService.getParticipatePost(userPk);
        return ApiResponse.onSuccess(PostConverter.toTotalPostViewResultDTO(participatePosts));
    }

    @GetMapping("/my")
    @Operation(summary = "본인이 생성한 투표글 조회", description = "본인이 생성한 투표글들을 조회하는 API입니다.")
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
  
    @GetMapping("/list")
    @Operation(summary = "글 목록 조회")
    public ApiResponse<Page<PostResponseDTO.PostListDTO>> getPostList(
            @RequestParam(required = false) Category category,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 10); // page=0부터 시작, size=10

        Page<Post> posts = postService.getPostList(category, pageable);

        Page<PostResponseDTO.PostListDTO> dtoPage =
                posts.map(PostConverter::toPostListDTO);

        return ApiResponse.onSuccess(dtoPage);
    }

    @GetMapping("/{postPk}")
    @Operation(summary = "글 상세 조회",description = "댓글을 제외한 세부정보를 제공합니다.")
    public ApiResponse<PostResponseDTO.PostDetailDTO> getPostDetail(
            @PathVariable Long postPk,
            @AuthenticationPrincipal JwtPrincipal user
    ) {
        return ApiResponse.onSuccess(postService.getPostDetail(postPk, user));
    }
}
