package hanium.cocam.config;

import hanium.cocam.jwt.JwtFilter;
import hanium.cocam.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    private static final String[] WHITE_LIST_URL = {
            "/api/users/login",
            "/api/users/signup",
            "/api/tutor/find",
            "/swagger",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api-docs/**",
            "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserService userService) throws Exception {
        http
                .csrf(auth -> auth.disable())
                .formLogin(auth -> auth.disable())
                .httpBasic(auth -> auth.disable())
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(new JwtFilter(userDetailsService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .headers();
        return http.build();
    }
}
