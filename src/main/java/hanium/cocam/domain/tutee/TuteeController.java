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
            description = "후배의 마이페이지 정보 조회입니다. <br><br>" +
                    "tuteeDetailResponse: 후배 상세 정보를 반환합니다.(마이 페이지)<br>" +
                    "requestedMentorshipListResponse: 내가 요청한 멘토십 리스트를 반환합니다. <br>" +
                    "ongoingMentorshipListResponse: 현재 진행 중인 멘토십 리스트를 반환합니다. <br>"
    )
    @GetMapping("/{id}/mypage")
    public ResponseEntity<ResponseDTO<TuteeMyPageResponse>> myPage(@PathVariable(name = "id") Long tuteeNo) {
        return ResponseEntity.ok().body(
                ResponseDTO.<TuteeMyPageResponse>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("후배 마이페이지 조회 완료")
                        .data(tuteeService.getMyPageDetail(tuteeNo))
                        .build()
        );
    }
    @Operation(
            summary = "후배 - 마이페이지 - 선배 상세보기 API",
            description = "후배의 마이페이지에서 매칭된 선배의 상세 정보를 조회하는 API입니다."
    )
    @GetMapping("/myTutor/detail/{mentorshipNo}")
    public ResponseEntity<ResponseDTO<hanium.cocam.domain.tutee.dto.TutorDetailResponse>> getMyTutorDetail(@PathVariable(name = "mentorshipNo") Long mentorshipNo) {
        hanium.cocam.domain.tutee.dto.TutorDetailResponse myTutorDetail = tuteeService.getMyTutorDetail(mentorshipNo);

        return ResponseEntity.ok().body(
                ResponseDTO.<TutorDetailResponse>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("튜터 상세 정보 조회 완료")
                        .data(myTutorDetail)
                        .build()
        );
    }
}