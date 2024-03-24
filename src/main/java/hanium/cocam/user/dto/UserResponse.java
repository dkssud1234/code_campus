package hanium.cocam.user.dto;

import hanium.cocam.user.User;
import hanium.cocam.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userNo;
    private String userId;
    private String userName;
    private String userEmail;
    private String userNickName;
    private String userSex;
    private String userPhone;
    private int userAge;
    private UserType userType;

    @Builder
    public UserResponse(User user) {
        this.userNo = user.getUserNo();
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.userNickName = user.getUserNickName();
        this.userSex = user.getUserSex();
        this.userPhone = user.getUserPhone();
        this.userAge = user.getUserAge();
        this.userType = user.getUserType();
    }
}
