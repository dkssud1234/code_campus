package hanium.cocam.user;

import hanium.cocam.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signup(request));
    }

    @PostMapping("/signup/profile")
    public ResponseEntity<Object> addProfile(@RequestBody AddProfileRequest request) {
        return ResponseEntity.ok().body(userService.addProfile(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(userService.login(request));
    }

    @GetMapping("/{userNo}")
    public ResponseEntity<UserResponse> findUser(@PathVariable(name = "userNo") Long userNo) {
        return ResponseEntity.ok(userService.findUser(userNo));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(userService.refreshToken(request).orElseThrow(() -> new RuntimeException("Refresh Token이 존재하지 않습니다.")));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest request) {
       return ResponseEntity.ok(userService.logout(request));
    }

}
