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
    private String [] mentorshipDay;
    private String mentorshipTime;
    private Category category;
    private String note; // 선배에게 하고 싶은 말

    public Mentorship toEntity(User tutor, User tutee) {
        return Mentorship.builder()
                .tutor(tutor)
                .tutee(tutee)
                .mentorshipDay(String.join(",",mentorshipDay))
                .mentorshipTime(mentorshipTime)
                .category(category)
                .mentorshipStatus("WAIT") // 기본값: 매칭 대기 상태
                .note(note)
                .build();
    }
}
