package hanium.cocam.domain.tutor.dto;

import hanium.cocam.domain.user.entity.Profile;
import hanium.cocam.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TutorListResponse {

    private Long userNo;
    private String userName;
    private String classType;
    private String userSex;
    private String tutorProfileImg;
    private String school;
    private String[] keyword;
    private String level;
    private String classArea;
    private int tutorLikes;

    @Builder
    public TutorListResponse(User user, Profile profile) {
        this.userNo = user.getUserNo();
        this.userName = user.getUserName();
        this.classType = profile.getClassType();
        this.userSex = user.getUserSex();
        this.tutorProfileImg = profile.getTutorProfileImg();
        this.school = profile.getSchool();
        this.keyword = profile.getKeywordArray();
        this.level = profile.getLevel();
        this.classArea = profile.getClassArea();
        this.tutorLikes = profile.getTutorLikes();
    }
}
