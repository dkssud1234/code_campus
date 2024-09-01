package hanium.cocam.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanium.cocam.domain.user.UserService;
import hanium.cocam.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final String secretKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION); //Bearer "accessToken"

        // Authorization header가 없거나 Bearer 토큰 형식이 아닌 경우
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("Authorization header not found");
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Not found authorization", null);
            return;
        }

        // Token 추출
        try {
            String token = authorization.split(" ")[1];
            Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);

            String userEmail = JwtUtil.getUserName(token, secretKey);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error("Expired token: {}", e.getMessage());
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Token expired", null);
        } catch (SignatureException e) {
            log.error("Invalid token signature: {}", e.getMessage());
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Invalid token", null);
        } catch (Exception e) {
            log.error("Token parsing error: {}", e.getMessage());
            sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Token error", null);
        }
    }

    private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message, String data) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        ResponseDTO<Object> errorResponse = ResponseDTO.builder()
                .result(false)
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
