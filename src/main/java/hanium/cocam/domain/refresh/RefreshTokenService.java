package hanium.cocam.domain.refresh;


import hanium.cocam.domain.user.UserRepository;
import hanium.cocam.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ZonedDateTime expiryDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plus(1, ChronoUnit.MINUTES);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByUserEmail(userEmail).get())
                .refreshToken(UUID.randomUUID().toString())
                .expiryDate(expiryDateTime.toInstant())
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByUser(User user) {
        return refreshTokenRepository.findByUser(user);
    }


    public Optional<RefreshToken> findByToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshToken.setExpiryDate(Instant.now().plus(1, ChronoUnit.MINUTES));
            return refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken isExistsRefreshToken(User user, String userEmail) {
        // 리프레쉬 토큰 확인 및 유효성 검증
        RefreshToken refreshToken;

        // 해당 유저로 검색해서 해당 유저가 리프레시 토큰이 있는지 확인
        Optional<RefreshToken> existingToken = findByUser(user);

        // 이미 있을경우
        if (existingToken.isPresent()) {
            refreshToken = verifyExpiration(existingToken.get());
        } else { // 없을 경우
            refreshToken = createRefreshToken(userEmail);
        }
        return refreshToken;
    }

    @Transactional
    public String deleteByToken(String refreshToken) {
        if(findByToken(refreshToken).isPresent()) {
            refreshTokenRepository.deleteByRefreshToken(refreshToken);
            return "로그아웃이 완료되었습니다.";
        } else {
            return "리프레시 토큰이 존재하지 않습니다.";
        }
    }
}