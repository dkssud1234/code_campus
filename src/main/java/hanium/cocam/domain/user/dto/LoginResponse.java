package hanium.cocam.domain.user.dto;


import hanium.cocam.domain.user.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String expiryDate;
    private Long userNo;
    private UserType userType;
    private String userEmail;
    private String userName;
}

