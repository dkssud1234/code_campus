package hanium.cocam.domain.tutee.dto;

import hanium.cocam.domain.user.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TuteeDetailResponse {
    private String tuteeProfileImg;
    private String name;
    private UserType userType;
}