package hanium.cocam.user.entity;

import hanium.cocam.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "TB_USERS")
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo; // 유저 번호
    private String userEmail;
    private String password;
    private String userName;
    @Enumerated(EnumType.STRING)
    private UserSex userSex;
    private String userPhone;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Profile profile;

    public Profile getProfile() {
        return this.profile;
    }
}
