package spring.hackerthon.comment.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.user.domain.User;
import spring.hackerthon.global.common.BaseEntity;

@Entity
@Table(name = "tb_comment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_pk")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private User user;

    @Column(length = 255)
    private String content;

    private Long likeCount = 0L;

}

