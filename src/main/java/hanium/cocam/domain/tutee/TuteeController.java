package hanium.cocam.domain.tutee;

import hanium.cocam.domain.tutee.dto.TuteeMyPageResponse;
import hanium.cocam.domain.tutee.dto.TutorDetailResponse;
import hanium.cocam.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutee")
@Slf4j
public class TuteeController {

    private final TuteeService tuteeService;

    @Operation(
            summary = "후배 - 마이페이지 API",
            description = "후배의 마이페이지 정보 조회입니다."
    )
    @GetMapping("/{id}/mypage")
    public ResponseEntity<ResponseDTO<TuteeMyPageResponse>> myPage(@PathVariable(name = "id") Long tuteeNo) {
        try {
            TuteeMyPageResponse response = tuteeService.getMyPageDetail(tuteeNo);
            return ResponseEntity.ok().body(
                    ResponseDTO.<TuteeMyPageResponse>builder()
                            .result(true)
                            .status(HttpStatus.OK.value())
                            .message("후배 마이페이지 조회 완료")
                            .data(response)
                            .build()
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    ResponseDTO.<TuteeMyPageResponse>builder()
                            .result(false)
                            .status(HttpStatus.FORBIDDEN.value())
                            .message(e.getMessage()) // "접근 권한이 없습니다" 메시지 전달
                            .data(null)
                            .build()
            );
        }
    }

    @Operation(
            summary = "후배 - 마이페이지 - 선배 상세보기 API",
            description = "후배의 마이페이지에서 매칭된 선배의 상세 정보를 조회하는 API입니다."
    )
    @GetMapping("/myTutor/detail/{mentorshipNo}")
    public ResponseEntity<ResponseDTO<TutorDetailResponse>> getMyTutorDetail(@PathVariable(name = "mentorshipNo") Long mentorshipNo) {
        try {
            TutorDetailResponse response = tuteeService.getMyTutorDetail(mentorshipNo);
            return ResponseEntity.ok().body(
                    ResponseDTO.<TutorDetailResponse>builder()
                            .result(true)
                            .status(HttpStatus.OK.value())
                            .message("튜터 상세 정보 조회 완료")
                            .data(response)
                            .build()
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ResponseDTO.<TutorDetailResponse>builder()
                            .result(false)
                            .status(HttpStatus.UNAUTHORIZED.value())
                            .message(e.getMessage()) // "접근 권한이 없습니다" 메시지 전달
                            .data(null)
                            .build()
            );
        }
    }
}