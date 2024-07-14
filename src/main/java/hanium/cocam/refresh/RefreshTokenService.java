package hanium.cocam.refresh;


import hanium.cocam.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshToken createRefreshToken(String userEmail) {
        ZonedDateTime expiryDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plus(14, ChronoUnit.DAYS);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByUserEmail(userEmail).get())
                .refreshToken(UUID.randomUUID().toString())
                .expiryDate(expiryDateTime.toInstant())
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(refreshToken.getRefreshToken() + "Refresh token이 만료되었습니다");
        }
        return refreshToken;
    }

    public String deleteByToken(String refreshToken) {
        if(findByToken(refreshToken).isPresent()) {
            refreshTokenRepository.deleteByRefreshToken(refreshToken);
            return "로그아웃이 완료되었습니다.";
        } else {
            return "리프레시 토큰이 존재하지 않습니다.";
        }
    }
}