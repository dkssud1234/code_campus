package hanium.cocam.refresh;

import hanium.cocam.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_REFRESH_TOKEN")
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenNo;
    private String refreshToken;
    private Instant expiryDate;
    @OneToOne
    @JoinColumn(name = "user_no")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}