package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.user.entity.Category;
import hanium.cocam.domain.user.entity.User;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "TB_MENTORSHIP")
public class Mentorship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentorshipNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_no", referencedColumnName = "userNo")
    private User tutor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutee_no", referencedColumnName = "userNo") // 후배(멘티)의 userNo를 참조
    private User tutee;
    private String mentorshipDay; // 수업 요일
    private String mentorshipTime; // 수업 시간
    @Enumerated(EnumType.STRING)
    private Category category;
    private String mentorshipStatus; // 튜터링 상태 ex) WAIT: 매칭 대기 상태(기본값), OK: 매칭 수락, NO: 매칭 거절,
    private String note; // 선배에게 하고 싶은 말 ex) 제발 수업 하게 해주세요
}
