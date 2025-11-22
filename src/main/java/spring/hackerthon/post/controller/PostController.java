package spring.hackerthon.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.hackerthon.crawling.dto.NewsResponseDTO;
import spring.hackerthon.crawling.service.SearchService;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.security.JwtPrincipal;
import spring.hackerthon.post.converter.PostConverter;
import spring.hackerthon.post.domain.Category;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostRequestDTO;
import spring.hackerthon.post.dto.PostResponseDTO;
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

}
