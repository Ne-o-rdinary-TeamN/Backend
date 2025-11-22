package spring.hackerthon.post.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.hackerthon.recommendation.domain.Recommendation;
import spring.hackerthon.user.domain.User;
import spring.hackerthon.global.common.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postPk;

    @Column(length = 30)
    private String title;

    private LocalDate startDate;
    private LocalDate finishDate;

    @Column(length = 50)
    private String url;   // 뉴스 링크

    @Column(length = 255)
    private String agree;      // 찬성측 의견
    @Column(length = 255)
    private String disagree;   // 반대측 의견

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long agreeCount;
    private Long disagreeCount;
    private Double agreeRate;
    private Double disagreeRate;

    private Long commentCount;
    private Long scrapsCount;
    private Long totalCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Recommendation> recommendations = new ArrayList<>();
}

