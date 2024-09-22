package hanium.cocam.domain.user.dto;

import hanium.cocam.domain.user.entity.User;
import hanium.cocam.domain.user.entity.UserSex;
import hanium.cocam.domain.user.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userNo;
    private String userEmail;
    private String userName;
    private String userSex;
    private String userPhone;
    private UserType userType;

    @Builder
    public UserResponse(User user) {
        this.userNo = user.getUserNo();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.userSex = user.getUserSex();
        this.userPhone = user.getUserPhone();
        this.userType = user.getUserType();
    }
}
