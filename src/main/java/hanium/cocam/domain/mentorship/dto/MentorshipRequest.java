package hanium.cocam.domain.mentorship.dto;

import hanium.cocam.domain.mentorship.Mentorship;
import hanium.cocam.domain.user.entity.Category;
import hanium.cocam.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorshipRequest {
    private Long tutorNo;
    private Long tuteeNo;
    private List<String> mentorshipDay; // 수업 요일을 리스트로 변경
    private List<String> mentorshipTime; // 수업 시간을 리스트로 변경
    private Category category;
    private String note; // 선배에게 하고 싶은 말

    public List<Mentorship> toEntities(User tutor, User tutee) {
        // 멘토십 엔티티 리스트 생성
        return mentorshipDay.stream()
                .flatMap(day -> mentorshipTime.stream()
                        .map(time -> Mentorship.builder()
                                .tutor(tutor)
                                .tutee(tutee)
                                .mentorshipDay(day)
                                .mentorshipTime(time)
                                .category(category)
                                .mentorshipStatus("WAIT") // 기본값: 매칭 대기 상태
                                .note(note)
                                .build()))
                .toList();
    }
}
