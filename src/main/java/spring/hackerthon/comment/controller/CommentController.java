package spring.hackerthon.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.hackerthon.comment.converter.CommentConverter;
import spring.hackerthon.comment.domain.Comment;
import spring.hackerthon.comment.dto.CommentRequestDTO;
import spring.hackerthon.comment.dto.CommentResponseDTO;
import spring.hackerthon.comment.dto.CommentsListRes;
import spring.hackerthon.comment.service.CommentService;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.global.security.JwtPrincipal;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //투표 기능 완성 후에 투표한 사람만 댓글 작성 가능한 로직 추가해야함
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

    //댓글 좋아요
    @PostMapping("/api/comment/{commentPk}/like")
    @Operation(summary = "댓글 좋아요", description = "댓글 좋아요")
    public ApiResponse<Boolean> likeComment(@AuthenticationPrincipal JwtPrincipal user, @PathVariable("commentPk") Long commentPk) {
        if(user == null) {
            throw new GeneralHandler(ErrorStatus.UNAUTHORIZED);
        }

        return ApiResponse.onSuccess(commentService.likeComment(user, commentPk));
    }

    //댓글 조회
    @GetMapping("/api/comment")
    @Operation(summary = "댓글 조회", description = "댓글 목록 조회(필터링)")
    public ApiResponse<CommentsListRes> getComments(@RequestParam("postPk") long postPk, @RequestParam("option") String option) {
        return ApiResponse.onSuccess(commentService.getComments(postPk, option));
    }
}
