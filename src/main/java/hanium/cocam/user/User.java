package hanium.cocam.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "TB_USER")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo; // 유저 번호
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private UserSex userSex;
    private String userPhone;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String tutorProfile; // 선배 프로필 사진
    private String tutorUniv; // 선배 소속 대학
    private String tutorClassNum; // 선배 학번(20학번, 21학번 ...)
    private String tutorMajor; // 선배 학과
    private String tutorIntro; // 선배 소개글 (자유 형식)
}
