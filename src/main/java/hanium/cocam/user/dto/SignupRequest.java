package hanium.cocam.user.dto;

import hanium.cocam.user.entity.User;
import hanium.cocam.user.entity.UserSex;
import hanium.cocam.user.entity.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class SignupRequest {
    private String userEmail;
    private String password;
    private String userName;
    private UserSex userSex;
    private String userPhone;
    private UserType userType;

    public User toEntity() {
        return User.builder()
                .userEmail(userEmail)
                .password(password)
                .userName(userName)
                .userSex(userSex)
                .userPhone(userPhone)
                .userType(userType)
                .build();
    }
}
