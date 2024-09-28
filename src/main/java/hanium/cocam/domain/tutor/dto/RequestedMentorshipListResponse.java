package hanium.cocam.domain.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class RequestedMentorshipListResponse {
    private Long tuteeNo;
    private String tuteeName;
    private String mentorshipTime;
    private String note; // 선배에게 하고 싶은 말
}
