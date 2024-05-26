package hanium.cocam.tutor.dto;

import hanium.cocam.user.Category;
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
    private String tutorProfile;
    private String tutorUniv;
    private Category category;
    private String level;
    private String lang;
    private String classArea;
    private int tutorLike;

    @Builder
    public TutorListResponse(User user) {
        this.userName = user.getUserName();
        this.tutorProfile = user.getTutorProfile();
        this.tutorUniv = user.getTutorUniv();
        this.category = user.getCategory();
        this.level = user.getLevel();
        this.lang = user.getLang();
        this.classArea = user.getClassArea();
        this.tutorLike = user.getTutorLikes();
    }
}
