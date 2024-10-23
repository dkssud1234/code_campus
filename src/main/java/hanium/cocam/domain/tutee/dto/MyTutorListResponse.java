package hanium.cocam.domain.tutee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyTutorListResponse {
    private Long mentorshipNo;
    private Long tutorNo;
    private String tutorName;
    private String mentorshipTime;
    private String mentorshipDate;
}
