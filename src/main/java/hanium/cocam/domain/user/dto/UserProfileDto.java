package hanium.cocam.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDto {
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
