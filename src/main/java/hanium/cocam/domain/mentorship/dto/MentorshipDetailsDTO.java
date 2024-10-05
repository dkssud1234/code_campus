package hanium.cocam.domain.mentorship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MentorshipDetailsDTO {
    private Long mentorshipNo;   // 멘토십 ID
    private Long tutorNo;        // 튜터 ID
    private String [] tutorKeywords; // 튜터 키워드
    private Long tuteeNo;        // 튜티 ID
    private String [] tuteeKeywords; // 튜티 키워드
    private String mentorshipDay;  // 수업 요일
    private String mentorshipTime; // 수업 시간
    private String note; // 선배에게 하고싶은말
}
