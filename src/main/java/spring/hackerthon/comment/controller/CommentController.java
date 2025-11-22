package spring.hackerthon.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.global.security.JwtPrincipal;

@Tag(name = "댓글 컨트롤러")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postPk}/comment")
    @Operation(summary = "투표글 댓글 작성", description = "투표글에 댓글을 작성할 수 있는 API입니다.")
    public ApiResponse<CommentResponseDTO.CommentCreateResultDTO> writeComment(
            @AuthenticationPrincipal JwtPrincipal user,
            @PathVariable("postPk") Long postPk,
            @RequestBody @Valid CommentRequestDTO.CommentCreateDTO request
            ) {

        Comment comment = commentService.createComment(user.userPk(), postPk, request);
        return ApiResponse.onSuccess(CommentConverter.toCommentCreateResultDTO(comment));
    }

    //댓글 좋아요
    @PostMapping("/api/comment/{commentPk}/like")
    @Operation(summary = "댓글 좋아요", description = "댓글 좋아요")
    public ApiResponse<Boolean> likeComment(@AuthenticationPrincipal JwtPrincipal user, @PathVariable("commentPk") Long commentPk) {
        if(user == null) {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }

        return ApiResponse.onSuccess(commentService.likeComment(user, commentPk));
    }
}
