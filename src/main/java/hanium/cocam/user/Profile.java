package hanium.cocam.user;

import hanium.cocam.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "TB_PROFILE")
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileNo;

    @OneToOne
    @JoinColumn(name = "user_no")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String keyword; // 키워드
    private String level; // 수업 레벨
    private String school; // 소속 대학

    // ============ 선배에 해당하는 부분 ============
    private String classArea;
    private String classType;
    private String tutorProfileImg; // 선배 프로필 사진
    private String tutorMajor; // 선배 학과
    private String tutorClassNum; // 선배 학번(20학번, 21학번 ...)
    private String tutorIntro; // 선배 소개글 (자유 형식)
    private String chatLink; // 오픈채팅 링크
    private String portLink; // 포트폴리오 링크
    private char authYN; // 학교 인증 유무(Y,N)
    private int tutorLikes; // 유저 좋아요 개수
    //  =========================================

    // ============ 후배에 해당하는 부분 ============
    private String studentType; // 학생 구분(대학생, 고등학생, 중학생)
    // =========================================

}
