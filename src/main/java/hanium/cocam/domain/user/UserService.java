package hanium.cocam.domain.user;

import hanium.cocam.domain.user.dto.*;
import hanium.cocam.jwt.JwtUtil;
import hanium.cocam.domain.refresh.RefreshToken;
import hanium.cocam.domain.refresh.RefreshTokenService;
import hanium.cocam.domain.user.entity.Profile;
import hanium.cocam.domain.user.entity.User;
import hanium.cocam.domain.user.entity.UserSex;
import hanium.cocam.domain.user.entity.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final ProfileRepository profileRepository;

    @Value("${jwt.secret}")
    private String secretKey;
    //    private Long expiredMs = 1000 * 60L;  // 토큰 유효시간 1분
    private Long expiredMs = 1000 * 60 * 60 * 8790L;  // 토큰 유효시간 1년(테스트용)

    @Transactional
    public String signup(SignupRequest request) {
        try {
            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            request.setPassword(encodedPassword);

            User user = User.builder()
                    .userEmail(request.getUserEmail())
                    .password(request.getPassword())
                    .userName(request.getUserName())
                    .userPhone(request.getUserPhone())
                    .userSex(request.getUserSex())
                    .userType(UserType.valueOf(request.getUserType()))
                    .profile(Profile.builder()
                            .keyword(String.join(",", request.getKeyword()))
                            .level(request.getLevel())
                            .school(request.getSchool())
                            .classArea(request.getClassArea())
                            .classType(request.getClassType())
                            .tutorProfileImg(request.getTutorProfileImg())
                            .tutorMajor(request.getTutorMajor())
                            .tutorClassNum(request.getTutorClassNum())
                            .tutorIntro(request.getTutorIntro())
                            .chatLink(request.getChatLink())
                            .portLink(request.getPortLink())
                            .tutorLikes(request.getTutorLikes())
                            .studentType(request.getStudentType())
                            .build())
                    .build();

            userRepository.save(user);
            profileRepository.save(user.getProfile());

            userRepository.findByUserEmail(request.getUserEmail()).orElseThrow(() -> new IllegalArgumentException("not found userEmail : " + request.getUserEmail()));

            return "회원가입 완료";

        } catch (IllegalArgumentException e) {
            // 중복된 이메일로 인한 예외 발생 시에는 그대로 전달
            return e.getMessage();
        } catch (Exception e) {
            // 그 외의 예외 발생 시에는 일반적인 오류 메시지 반환
            return "회원가입 중 오류가 발생했습니다. " + e.getMessage();
        }
    }

    public void isDuplicateUserEmail(String userEmail) {
        Optional<User> findUserId = userRepository.findByUserEmail(userEmail);
        if (findUserId.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일 입니다.");
        }
    }

    public Object login(LoginRequest request) {
        String userEmail = request.getUserEmail();
        String password = request.getPassword();

        // 존재하는 사용자인지 확인
        Optional<User> findUser = userRepository.findByUserEmail(userEmail);

        // 사용자가 존재하고 비밀번호가 일치하는 경우에만 인증 성공
        if (findUser.isPresent() && passwordEncoder.matches(password, findUser.get().getPassword())) {
            User user = findUser.get();
            String userName = user.getUserName();
            Long userNo = user.getUserNo();

            // 리프레쉬 토큰 확인 및 유효성 검증
            RefreshToken refreshToken = refreshTokenService.isExistsRefreshToken(user, userEmail);

            String accessToken = JwtUtil.createJwt(userEmail, userNo, secretKey, expiredMs);

            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken.getRefreshToken())
                    .expiryDate(JwtUtil.getExpirationDate(accessToken, secretKey))
                    .userNo(userNo)
                    .userEmail(userEmail)
                    .userName(userName)
                    .build();
        } else {
            return new LoginFailResponse("입력하신 아이디 또는 비밀번호를 확인해주세요.");
        }
    }

    public List<UserResponse> findAll() {
        List<UserResponse> users = userRepository.findAll().stream().map(UserResponse::new).toList();
        return users;
    }

    public UserResponse findUser(Long userNo) {
        User findUser = userRepository.findById(userNo).orElseThrow(() -> new IllegalArgumentException("not found "+ userNo));
        return UserResponse.builder()
                .user(findUser)
                .build();
    }

    public Optional<LoginResponse> issueAccessToken(RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String newAccessToken = JwtUtil.createJwt(user.getUserEmail(), user.getUserNo(), secretKey, expiredMs);
                    return LoginResponse.builder()
                            .accessToken(newAccessToken)
                            .refreshToken(request.getRefreshToken())
                            .expiryDate(JwtUtil.getExpirationDate(newAccessToken, secretKey))
                            .userNo(user.getUserNo())
                            .userEmail(user.getUserEmail())
                            .userName(user.getUserName())
                            .build();
                });
    }

    public String logout(LogoutRequest request) {
        return refreshTokenService.deleteByToken(request.getRefreshToken());
    }
}
