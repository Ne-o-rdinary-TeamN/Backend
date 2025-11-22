package spring.hackerthon.recommendation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.hackerthon.Post.domain.Post;

@Entity
@Table(name = "tb_recommendation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommendation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationPk;

    private String title;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_pk")
    private Post post;
}
