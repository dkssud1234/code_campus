package hanium.cocam.user;

import hanium.cocam.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "TB_USERS")
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo; // 유저 번호
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private UserSex userSex;
    private String userPhone;
    private String userProfile; // 유저 프로필 (기본사진)
    private UserType userType; // 유저 타입(대학생, 고등학생, 중학생)
    private String userLang; // 후배 배울 언어 (리액트, 스프링, 자바, ...)
    private String userLevel; // 후배 레벨 (입문, 초급, 중급이상)
}
