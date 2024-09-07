package hanium.cocam.domain.refresh;


import hanium.cocam.domain.user.UserRepository;
import hanium.cocam.domain.user.entity.User;
import hanium.cocam.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public RefreshToken createRefreshToken(String userEmail) {
//        테스트용(유효시간:2분)
        ZonedDateTime expiryDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plus(2, ChronoUnit.MINUTES);
//        실제 운영용(유효시간:2달)
//        ZonedDateTime expiryDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plus(2, ChronoUnit.MONTHS);

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
        // 유효기간이 지나면 5분 연장하고 재발급
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshToken.setExpiryDate(Instant.now().plus(2, ChronoUnit.MINUTES));
            return refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;  // 변경없이 반환
    }

    @Transactional
    public RefreshToken isExistsRefreshToken(User user, String userEmail) {
        Optional<RefreshToken> existingToken = findByUser(user);
        if (existingToken.isPresent()) {
            RefreshToken refreshToken = existingToken.get();
            if (refreshToken.getExpiryDate().compareTo(Instant.now()) > 0) {
                return refreshToken; // 만료되지 않았다면 바로 반환
            } else {
                return verifyExpiration(refreshToken); // 만료된 경우 처리
            }
        } else {
            return createRefreshToken(userEmail); // 없으면 새로 생성
        }
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