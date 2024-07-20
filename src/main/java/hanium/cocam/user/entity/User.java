package hanium.cocam.user.entity;

import hanium.cocam.base.BaseEntity;
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
@Table(name = "TB_USERS")
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long userNo; // 유저 번호
    private String userEmail;
    private String password;
    private String userName;
    @Enumerated(EnumType.STRING)
    private UserSex userSex;
    private String userPhone;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToOne
    @JoinColumn(name = "profile_no")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profile profile;
}
