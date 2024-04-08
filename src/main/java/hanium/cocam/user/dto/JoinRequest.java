package hanium.cocam.user.dto;

import hanium.cocam.user.User;
import hanium.cocam.user.UserSex;
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
    private UserSex userSex;
    private String userPhone;
    private UserType userType;
    private String tutorProfile; // 멘토 프로필 사진
    private String tutorUniv; // 멘토 소속 대학
    private String tutorClassNum; // 멘토 학번(20학번, 21학번 ...)
    private String tutorMajor; // 멘토 학과
    private String tutorIntro; // 멘토 소개글 (자유 형식)

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .userEmail(userEmail)
                .userSex(userSex)
                .userPhone(userPhone)
                .userType(userType)
                .tutorProfile(tutorProfile)
                .tutorUniv(tutorUniv)
                .tutorClassNum(tutorClassNum)
                .tutorMajor(tutorMajor)
                .tutorIntro(tutorIntro)
                .build();
    }
}
