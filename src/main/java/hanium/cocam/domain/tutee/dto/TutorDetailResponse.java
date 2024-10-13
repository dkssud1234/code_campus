package hanium.cocam.domain.tutee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TutorDetailResponse {
    private Long tutorNo;                // 튜터의 번호
    private String name;                 // 튜터의 이름
    private String[] keywordList;        // 키워드 리스트 (category)
    private String[] mentorshipDay;      // 멘토십 요일 (중복 가능)
    private String mentorshipTime;       // 멘토십 시간대
    private String classLevel;           // 튜터의 수업 가능 레벨
    private String tutorIntro;           // 튜터 소개
}