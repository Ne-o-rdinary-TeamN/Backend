package spring.hackerthon.user.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.hackerthon.global.common.BaseEntity;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

}