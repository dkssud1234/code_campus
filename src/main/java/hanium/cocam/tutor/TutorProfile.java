package hanium.cocam.tutor;

import hanium.cocam.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "TB_TUTOR_PROFILE")
@Builder
public class TutorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileNo;

    @OneToOne
    @JoinColumn(name = "user_no")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
