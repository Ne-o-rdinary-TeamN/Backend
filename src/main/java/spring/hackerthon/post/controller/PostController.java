package spring.hackerthon.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.post.converter.PostConverter;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostRequestDTO;
import spring.hackerthon.post.dto.PostResponseDTO;
import spring.hackerthon.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ApiResponse<PostResponseDTO.PostCreateResponseDTO> createPost(
//            @AuthenticationPrincipal JwtPrincipal user,
            @RequestPart(value = "request") @Valid PostRequestDTO.PostCreateRequestDTO request) {
        Post post = postService.joinPost(request);
        return ApiResponse.onSuccess(PostConverter.toPostCreateResponseDTO(post));
    }
}
