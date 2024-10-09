package hanium.cocam.domain.tutor;

import hanium.cocam.domain.tutor.dto.*;
import hanium.cocam.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutor")
@Slf4j
public class TutorController {

    private final TutorService tutorService;

    @Operation(
            summary = "튜터 조회 API"
    )
    @PostMapping("/find")
    public ResponseEntity<ResponseDTO<List<TutorListResponse>>> findTutor(@RequestBody(required = false) TutorSearchCond tutorSearchCond) {
        List<TutorListResponse> findTutors;

        if (tutorSearchCond != null) {
            findTutors = tutorService.findTutor(tutorSearchCond);
        } else {
            findTutors = tutorService.findTutor();
        }

        return ResponseEntity.ok().body(
                ResponseDTO.<List<TutorListResponse>>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("튜터 조회 완료")
                        .data(findTutors)
                        .build()
        );
    }

    @Operation(
            summary = "선배 탐색 - 선배 프로필 상세보기 API",
            description = "선배의 프로필 정보 조회 입니다."
    )
    @GetMapping("/profile/{id}")
    public ResponseEntity<ResponseDTO<TutorProfileResponse>> profileDetail(@PathVariable(name = "id") Long tutorNo) {
        return ResponseEntity.ok().body(
                ResponseDTO.<TutorProfileResponse>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("선배 소개 상세보기 조회 완료")
                        .data(tutorService.profileDetail(tutorNo))
                        .build()
        );
    }

    @Operation(
            summary = "선배 - 마이페이지 API",
            description = "선배의 나의 정보 조회 입니다. <br><br>" +
                    "tutorDetailResponse: 선배 상세 정보를 반환합니다.(마이 페이지)<br>" +
                    "myTuteeListResponse: 후배 관리 - 수업 진행 중인 후배 list를 반환합니다. <br>" +
                    "requestedMentorshipListResponse: 매칭 요청 리스트 - 나에게 매칭 요청을 한 후배 list를 반환합니다. <br>"
    )
  
    @GetMapping("/{id}/detail")
    public ResponseEntity<ResponseDTO<TutorMyPageResponse>> myPage(@PathVariable(name = "id") Long tutorNo) {
        return ResponseEntity.ok().body(
                ResponseDTO.<TutorMyPageResponse>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("선배 마이페이지 조회 완료")
                        .data(tutorService.detail(tutorNo))
                        .build()
        );
    }

    @Operation(
            summary = "선배 - 마이페이지 - 후배 상세보기(후배 관리, 매칭 관리) API",
            description = "후배관리, 매칭관리 - 후배 정보 상세 보기 API입니다."
    )
    @GetMapping("/myTutee/detail/{mentorshipNo}")
    public ResponseEntity<ResponseDTO<TuteeDetailResponse>> getMytuteeDetail(@PathVariable(name = "mentorshipNo") Long mentorshipNo) {
        TuteeDetailResponse myTuteeDetail = tutorService.getMytuteeDetail(mentorshipNo);
      
        return ResponseEntity.ok().body(
                ResponseDTO.<TuteeDetailResponse>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("튜티 상세 정보 조회 완료")
                        .data(myTuteeDetail)
                        .build()
        );
    }
  
    @Operation(
            summary = "후배 삭제 API",
            description = "특정 멘토십 번호에 해당하는 후배와의 매칭을 삭제합니다."
    )
    @DeleteMapping("/mentorship/{mentorshipNo}/delete")
    public ResponseEntity<ResponseDTO<Void>> deleteTuteeByMentorship(@PathVariable(name = "mentorshipNo") Long mentorshipNo) {
        tutorService.deleteTuteeByMentorship(mentorshipNo);
        return ResponseEntity.ok().body(
                ResponseDTO.<Void>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("후배 삭제 완료")
                        .build()
        );
    }
}
