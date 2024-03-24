package hanium.cocam.classes.dto;

import hanium.cocam.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassListResponse {
    private String userNickName;
    private String mentorProfile; // 멘토 프로필 사진
    private String mentorUniv; // 멘토 소속 대학
    private String mentorMajor; // 멘토 학과

    @Builder
    public ClassListResponse(User user) {
        this.userNickName = user.getUserNickName();
        this.mentorProfile = user.getMentorProfile();
        this.mentorUniv = user.getMentorUniv();
        this.mentorMajor = user.getMentorMajor();
    }
}
