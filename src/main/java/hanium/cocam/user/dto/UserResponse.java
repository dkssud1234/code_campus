package hanium.cocam.user.dto;

import hanium.cocam.user.entity.User;
import hanium.cocam.user.entity.UserSex;
import hanium.cocam.user.entity.UserType;
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
    private UserSex userSex;
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
