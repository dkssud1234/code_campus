package hanium.cocam.refresh;

import hanium.cocam.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private User user;
}