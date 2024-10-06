package hanium.cocam.domain.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyTuteeListResponse {
    private Long mentorshipNo;
    private Long tuteeNo;
    private String tuteeName;
    private String mentorshipTime;
}