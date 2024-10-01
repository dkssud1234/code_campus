package hanium.cocam.domain.mentorship.dto;

import hanium.cocam.domain.mentorship.Mentorship;
import hanium.cocam.domain.user.entity.Category;
import hanium.cocam.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorshipAcceptRequest {
    private Long mentorshipNo;
    private String status;
}
