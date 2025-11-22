package spring.hackerthon.Post.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.hackerthon.User.domain.User;
import spring.hackerthon.global.common.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "tb_post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_Pk;

    @Column(length = 30)
    private String title;

    private LocalDate start_Date;
    private LocalDate finish_Date;

    @Column(length = 50)
    private String url;   // 뉴스 링크

    @Column(length = 255)
    private String agree;      // 찬성측 의견
    @Column(length = 255)
    private String disagree;   // 반대측 의견

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long agree_Count;
    private Long disagree_Count;
    private Double agree_Rate;
    private Double disagree_Rate;

    private Long comment_Count;
    private Long scraps_Count;
    private Long total_Count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private User user;
}

