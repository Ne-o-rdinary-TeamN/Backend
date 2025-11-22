package spring.hackerthon.news.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_news")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsPk;

    @Column(length = 30)
    private String newsTitle;

    @Column(length = 50)
    private String url;
}
