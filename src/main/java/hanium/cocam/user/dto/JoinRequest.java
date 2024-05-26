package hanium.cocam.user.dto;

import hanium.cocam.user.Category;
import hanium.cocam.user.User;
import hanium.cocam.user.UserSex;
import hanium.cocam.user.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

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
    private String tutorProfile;
    private String tutorUniv;
    private String tutorMajor;
    private String tutorClassNum;
    private String classArea;
    private String classType;
    private Category category;
    private String level;
    private String lang;
    private String tutorIntro;
    private String chatLink;
    private String portLink;
    private int tutorLikes=0;
    private String authYN = "N";

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
                .tutorMajor(tutorMajor)
                .tutorClassNum(tutorClassNum)
                .classArea(classArea)
                .classType(classType)
                .category(category)
                .lang(lang)
                .level(level)
                .tutorIntro(tutorIntro)
                .chatLink(chatLink)
                .portLink(portLink)
                .tutorLikes(tutorLikes)
                .authYN(authYN)
                .build();
    }
}
