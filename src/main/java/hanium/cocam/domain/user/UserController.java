package hanium.cocam.domain.user;

import hanium.cocam.domain.user.dto.*;
import hanium.cocam.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO<?>> signup(@RequestBody SignupRequest request) {
        String msg = userService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseDTO.builder()
                        .result(true)
                        .status(HttpStatus.CREATED.value())
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

        // 예외 처리
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

    /**
     * 액세스 토큰 재발급
     * @param request
     * @return
     */
    @PostMapping("/issueAccessToken")
    public ResponseEntity<ResponseDTO<LoginResponse>> issueAccessToken(@RequestHeader(value = "Authorization") String authorizationHeader,
                                                                       @RequestBody RefreshTokenRequest request) {
        LoginResponse loginResponse = userService.issueAccessToken(authorizationHeader, request)
                .orElseThrow(() -> new RuntimeException("Refresh Token이 존재하지 않거나 유효하지 않습니다."));

        return ResponseEntity.ok(
                ResponseDTO.<LoginResponse>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("액세스 토큰 재발급 완료")
                        .data(loginResponse)
                        .build()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest request) {
        return ResponseEntity.ok(userService.logout(request));
    }

    @GetMapping("/isDuplicate/{userEmail}")
    public ResponseEntity<Boolean> isDuplicateEmail(@PathVariable(name = "userEmail") String userEmail) {
        try {
            userService.isDuplicateUserEmail(userEmail);
            // 중복이 아닐 때 true 반환
            return ResponseEntity.ok(true);
        } catch (IllegalArgumentException e) {
            // 중복일 때 false 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

}

