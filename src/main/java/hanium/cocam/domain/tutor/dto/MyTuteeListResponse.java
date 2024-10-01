package hanium.cocam.domain.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class MyTuteeListResponse {
    private Long tuteeNo;
    private String tuteeName;
    private String mentorshipTime;
}