package hanium.cocam.domain.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestedMentorshipListResponse {
    private Long mentorshipNo;
    private Long tuteeNo;
    private String tuteeName;
    private String mentorshipTime;
    private String note; // 선배에게 하고 싶은 말
}
