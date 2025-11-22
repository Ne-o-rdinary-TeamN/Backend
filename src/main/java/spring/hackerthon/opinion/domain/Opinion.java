package spring.hackerthon.opinion.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.user.domain.User;

@Entity
@Table(name = "tb_opinion")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Opinion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opinionPk;

    @Enumerated(EnumType.STRING)
    private OpinionType opinion;   // AGREE / DISAGREE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_pk")
    private Post post;
}
