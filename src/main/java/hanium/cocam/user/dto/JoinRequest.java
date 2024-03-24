package hanium.cocam.user.dto;

import hanium.cocam.user.User;
import hanium.cocam.user.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class JoinRequest {
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String userNickName;
    private String userSex;
    private String userPhone;
    private int userAge;
    private UserType userType;
    private String mentorProfile; // 멘토 프로필 사진
    private String mentorUniv; // 멘토 소속 대학
    private String mentorClassNum; // 멘토 학번(20학번, 21학번 ...)
    private String mentorMajor; // 멘토 학과
    private String mentorIntro; // 멘토 소개글 (자유 형식)
    private String mentorMbti; // 멘토 Mbti

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .userEmail(userEmail)
                .userNickName(userNickName)
                .userSex(userSex)
                .userPhone(userPhone)
                .userAge(userAge)
                .userType(userType)
                .mentorProfile(mentorProfile)
                .mentorUniv(mentorUniv)
                .mentorClassNum(mentorClassNum)
                .mentorMajor(mentorMajor)
                .mentorIntro(mentorIntro)
                .mentorMbti(mentorMbti)
                .build();
    }
}
