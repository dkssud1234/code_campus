package hanium.cocam.domain.user;

import hanium.cocam.domain.user.dto.*;
import hanium.cocam.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "회원가입 API"
    )
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

    @Operation(
            summary = "로그인 API"
    )
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

    @Operation(
            summary = "유저 상세보기 API"
    )
    @GetMapping("/detail/{userNo}")
    public ResponseEntity<UserResponse> findUser(@PathVariable(name = "userNo") Long userNo) {
        return ResponseEntity.ok(userService.findUser(userNo));
    }

    /**
     * 액세스 토큰 재발급
     * @param request
     * @return
     */
    @Operation(
            summary = "액세스 토큰 재발급 API",
            description = "body 안에 refresh token을 보내야합니다. <br>" +
                    "accessToken은 authorizationHeader에서 파싱하여 확인합니다."
    )
    //@RequestHeader(value = "Authorization") String authorizationHeader,
    @PostMapping("/issueAccessToken")
    public ResponseEntity<ResponseDTO<LoginResponse>> issueAccessToken(@RequestBody RefreshTokenRequest request) {
        LoginResponse loginResponse = userService.issueAccessToken(request)
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

    @Operation(
            summary = "로그아웃 API"
    )
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest request) {
        return ResponseEntity.ok(userService.logout(request));
    }

    @Operation(
            summary = "이메일 중복 체크 API",
            description = "유저의 이메일의 중복을 확인합니다. <br>" +
                    "true: 사용 가능한 이메일 / false: 중복된 이메일"
    )
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

