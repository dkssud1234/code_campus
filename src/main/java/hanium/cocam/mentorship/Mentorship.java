package hanium.cocam.mentorship;

import hanium.cocam.user.entity.Category;
import hanium.cocam.user.entity.User;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
