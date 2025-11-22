package spring.hackerthon.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spring.hackerthon.global.common.BaseEntity;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_user")
@Getter
@Setter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPk;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String userId;

    @Column(length = 255)
    private String userPw;

    @Column(length = 20)
    private String nickname;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}