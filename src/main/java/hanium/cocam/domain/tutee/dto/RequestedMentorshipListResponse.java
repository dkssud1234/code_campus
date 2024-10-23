package hanium.cocam.domain.tutee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestedMentorshipListResponse {
    private Long mentorshipNo;
    private Long tutorNo;
    private String tutorName;
    private String mentorshipTime;
    private String note; // 튜터에게 하고 싶은 말
    private String mentorshipStatus;
}