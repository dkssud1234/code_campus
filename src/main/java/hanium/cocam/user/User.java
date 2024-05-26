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
    private String classType; // 수업 방식(온라인, 오프라인, 둘다)
    private String classArea; // 수업 지역(도, 시, 구, 동)
    @Enumerated(EnumType.STRING)
    private Category category; // 후배, 선배 카테고리(백엔드, 프론트엔드, 보안, ...)
    private String level; // 후배, 선배 수업 레벨(입문, 초급, 중급이상)
    private String lang; // 후배, 선배 언어(리액트, 자바, ...)
    private String tutorProfile; // 선배 프로필 사진
    private String tutorUniv; // 선배 소속 대학
    private String tutorClassNum; // 선배 학번(20학번, 21학번 ...)
    private String tutorMajor; // 선배 학과
    private String tutorIntro; // 선배 소개글 (자유 형식)
    private int tutorLikes; // 유저 좋아요 개수
    private String chatLink; // 오픈채팅 링크
    private String portLink; // 포트폴리오 링크
    private String authYN; // 학교 인증 유무(Y,N)
}
