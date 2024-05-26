package hanium.cocam.tutor.dto;

import hanium.cocam.user.Category;
import hanium.cocam.user.Profile;
import hanium.cocam.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TutorListResponse {
    private String userName;
    private String tutorProfileImg;
    private String school;
    private String keyword;
    private String level;
    private String classArea;
    private int tutorLikes;

    @Builder
    public TutorListResponse(User user, Profile profile) {
        this.userName = user.getUserName();
        this.tutorProfileImg = profile.getTutorProfileImg();
        this.school = profile.getSchool();
        this.keyword = profile.getKeyword();
        this.level = profile.getLevel();
        this.classArea = profile.getClassArea();
        this.tutorLikes = profile.getTutorLikes();
    }
}
