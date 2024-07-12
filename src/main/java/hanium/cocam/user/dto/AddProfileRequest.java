package hanium.cocam.user.dto;

import hanium.cocam.user.Profile;
import hanium.cocam.user.User;
import hanium.cocam.user.UserSex;
import hanium.cocam.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddProfileRequest {
    private Long userNo;
    private UserType userType;
    private String[] keyword;
    private String level;
    private String school;
    private String classType;
    private String classArea;
    private String tutorMajor; // 선배 학과
    private String tutorClassNum; // 선배 학번(20학번, 21학번, ...)
    private String tutorIntro; // 선배 소개글 (자유 형식)
    private String chatLink; // 오픈채팅 링크
    private String portLink; // 포트폴리오 링크
    private char authYN='N'; // 학교 인증 유무(Y, N)
    private int tutorLikes=0; // 유저 좋아요 개수
    private String studentType; // 학생 구분(대학생, 고등학생, 중학생)

    public Profile toEntity(User user) {
        String keywordString = String.join(",", this.keyword);
        return Profile.builder()
                .user(user)
                .userType(userType)
                .keyword(keywordString)
                .classType(classType)
                .classArea(classArea)
                .level(level)
                .school(school)
                .tutorMajor(tutorMajor)
                .tutorClassNum(tutorClassNum)
                .tutorIntro(tutorIntro)
                .chatLink(chatLink)
                .portLink(portLink)
                .authYN(authYN)
                .tutorLikes(tutorLikes)
                .studentType(studentType)
                .build();
    }
}
