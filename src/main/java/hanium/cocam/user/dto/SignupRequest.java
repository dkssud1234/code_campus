package hanium.cocam.user.dto;

import hanium.cocam.user.entity.Profile;
import hanium.cocam.user.entity.User;
import hanium.cocam.user.entity.UserSex;
import hanium.cocam.user.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class SignupRequest {
    private String userEmail;
    private String password;
    private String userName;
    private String userPhone;
    private String userSex;
    private String userType;
    private String[] keyword;
    private String level;
    private String school;
    private String classArea;
    private String classType;
    private String tutorProfileImg;
    private String tutorMajor;
    private String tutorClassNum;
    private String tutorIntro;
    private String chatLink;
    private String portLink;
    private char authYN;
    private int tutorLikes;
    private String studentType;
}
