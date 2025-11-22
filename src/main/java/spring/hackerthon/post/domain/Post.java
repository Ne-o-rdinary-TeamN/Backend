package spring.hackerthon.post.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.hackerthon.opinion.domain.OpinionType;
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

    @Column(length = 255)
    private String title;

    @Column(length = 255)
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
    private Long totalCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Recommendation> recommendations = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Hashtag> hashtags = new ArrayList<>();

    public void updatePost(OpinionType type) {
        //투표 수 수정
        if(type.equals(OpinionType.AGREE)) {
            //동의인 경우
            this.agreeCount = this.agreeCount + 1;
        } else {
            this.disagreeCount = this.disagreeCount + 1;
        }

        this.totalCount = this.totalCount + 1;

        //비율 수정
        double agreeRateRaw = ((double) this.agreeCount / this.totalCount) * 100;
        this.agreeRate = Math.round(agreeRateRaw * 10.0) / 10.0; // 소수점 첫째 자리 반올림
        this.disagreeRate = 100.0 - this.agreeRate;
    }
}

