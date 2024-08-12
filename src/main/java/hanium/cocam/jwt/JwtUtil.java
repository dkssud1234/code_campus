package hanium.cocam.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JwtUtil {

    public static String getUserName(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("userEmail", String.class);
    }

    public static Long getUserNo(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody().get("userNo", Long.class);
    }

    public static String getExpirationDate(String token, String secretKey) {
        Date expirationDate = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul")); // 로컬 시간대로 설정

        return dateFormat.format(expirationDate);
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    public static String createJwt(String userEmail, Long userNo, String secretKey, Long expiredMs) {
        Claims claims = Jwts.claims();
        claims.put("userEmail", userEmail);
        claims.put("userNo", userNo);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }
}
