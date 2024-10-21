package hanium.cocam.domain.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TuteeDetailResponse {
    private Long tuteeNo;                // 튜티의 번호
    private String name;                 // 튜티의 이름
    private String[] keywordList;    // 키워드 리스트 (category)
    private String[] mentorshipDay;  // 멘토십 요일 (중복 가능)
    private String mentorshipTime;       // 멘토십 시간대
    private String classLevel;
    private String note;                 // 튜티의 노트
}
