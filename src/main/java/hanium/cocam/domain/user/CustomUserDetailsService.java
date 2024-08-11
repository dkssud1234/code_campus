package hanium.cocam.domain.user;

import hanium.cocam.domain.user.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import static java.util.Collections.singletonList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            singletonList(new SimpleGrantedAuthority("USER"))
        );
    }
}
