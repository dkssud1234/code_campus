package hanium.cocam.jwt;


import hanium.cocam.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION); //Bearer "accessToken"

        //token 안보내면 block 처리
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("not found authorization");
            filterChain.doFilter(request, response);
            return;
        }

        // token 추출
        String token = authorization.split(" ")[1];

        // token expired 여부
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("만료된 token 입니다");
            filterChain.doFilter(request, response);
            return;
        }

        // username token 에서 꺼내기
        String username = JwtUtil.getUserName(token, secretKey);

        // UserDetails 로드
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


        // Detail 넣기
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
