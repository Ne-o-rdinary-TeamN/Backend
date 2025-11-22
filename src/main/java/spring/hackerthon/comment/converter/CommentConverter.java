package spring.hackerthon.comment.converter;

import spring.hackerthon.comment.domain.Comment;
import spring.hackerthon.comment.dto.CommentResponseDTO;

public class CommentConverter {

    public static CommentResponseDTO.CommentCreateResultDTO toCommentCreateResultDTO(Comment comment) {
        return CommentResponseDTO.CommentCreateResultDTO.builder()
                .commentPk(comment.getCommentPk())
                .build();
    }
}
