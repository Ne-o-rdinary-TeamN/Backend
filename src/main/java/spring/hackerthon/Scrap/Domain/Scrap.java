package spring.hackerthon.Scrap.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.hackerthon.Post.domain.Post;
import spring.hackerthon.User.domain.User;


@Entity
@Table(name = "tb_scrap")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scrap {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrap_Pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_pk")
    private Post post;
}
