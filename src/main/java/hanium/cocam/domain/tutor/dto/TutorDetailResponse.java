package hanium.cocam.domain.tutor.dto;

import hanium.cocam.domain.user.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TutorDetailResponse {
    private String tutorProfileImg;
    private String name;
    private UserType userType;
}
