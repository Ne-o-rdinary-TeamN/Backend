package spring.hackerthon.comment.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.hackerthon.user.domain.User;


@Entity
@Table(name = "tb_comment_like")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentLikePk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_pk")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private User user;
}
