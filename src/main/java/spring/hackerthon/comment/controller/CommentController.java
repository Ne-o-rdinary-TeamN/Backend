package spring.hackerthon.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.hackerthon.comment.converter.CommentConverter;
import spring.hackerthon.comment.domain.Comment;
import spring.hackerthon.comment.dto.CommentRequestDTO;
import spring.hackerthon.comment.dto.CommentResponseDTO;
import spring.hackerthon.comment.service.CommentService;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.security.JwtPrincipal;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postPk}/comment")
    @Operation(summary = "투표글 댓글 작성 API", description = "투표글에 댓글을 작성할 수 있는 API입니다.")
    public ApiResponse<CommentResponseDTO.CommentCreateResultDTO> writeComment(
            @AuthenticationPrincipal JwtPrincipal user,
            @PathVariable("postPk") Long postPk,
            @RequestBody @Valid CommentRequestDTO.CommentCreateDTO request
            ) {

        Comment comment = commentService.createComment(user.userPk(), postPk, request);
        return ApiResponse.onSuccess(CommentConverter.toCommentCreateResultDTO(comment));
    }
}
