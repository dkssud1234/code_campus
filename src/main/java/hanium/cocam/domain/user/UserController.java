package hanium.cocam.domain.user;

import hanium.cocam.domain.user.dto.*;
import hanium.cocam.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<?>> signup(@RequestBody SignupRequest request) {
        String msg = userService.signup(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDTO.builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message(msg)
                        .data(null)
                        .build());

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<?>> login(@RequestBody LoginRequest request) {
        Object response = userService.login(request);

        if (response instanceof LoginResponse) {
            // 로그인 성공
            return ResponseEntity.ok().body(ResponseDTO.builder()
                    .result(true)
                    .status(HttpStatus.OK.value())
                    .message("로그인 완료")
                    .data(response)
                    .build());
        } else if (response instanceof LoginFailResponse) {
            // 로그인 실패
            return ResponseEntity.ok().body(ResponseDTO.builder()
                    .result(false)
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .message("로그인 실패")
                    .data(null)
                    .build());
        }

        // 예외처리
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDTO.builder()
                .result(false)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("서버 오류")
                .data(null)
                .build());
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
