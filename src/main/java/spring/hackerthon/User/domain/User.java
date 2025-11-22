package spring.hackerthon.User.domain;

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
    private Long user_pk;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String user_id;

    @Column(length = 255)
    private String user_pw;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}