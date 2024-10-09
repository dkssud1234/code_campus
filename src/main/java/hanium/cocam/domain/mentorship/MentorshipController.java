package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.mentorship.dto.MentorshipAcceptRequest;
import hanium.cocam.domain.mentorship.dto.MentorshipKeywordsResponse;
import hanium.cocam.domain.mentorship.dto.MentorshipRequest;
import hanium.cocam.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentorship")
@RequiredArgsConstructor
public class MentorshipController {

    private final MentorshipService mentorshipService;

    @Operation(
            summary = "튜터 매칭 요청 API",
            description = "튜터와 튜티의 id를 받아 매칭 요청합니다 <br>" +
                    "튜터의 id는 해당 유저의 id를 가져오고, 튜터의 id는 로그인할 때 리턴한 유저의 id를 사용합니다."
    )
    @PostMapping("/request")
    public ResponseEntity<String> requestMentorship(@RequestBody MentorshipRequest mentorshipRequest) {
        return ResponseEntity.ok().body(mentorshipService.requestMentorship(mentorshipRequest));
    }
    @Operation(
            summary = "튜터와 튜티의 키워드 조회 API",
            description = "튜터 번호(tutorNo)와 로그인한 튜티 번호(tuteeNo)를 받아 튜터와 튜티의 키워드를 모두 조회합니다."
    )
    @GetMapping("/show-keyword")
    public ResponseEntity<MentorshipKeywordsResponse> showKeywords(
            @RequestParam(name = "tutorNo") Long tutorNo,
            @RequestParam(name = "tuteeNo") Long tuteeNo) {

        MentorshipKeywordsResponse keywords = mentorshipService.getMentorshipKeywords(tutorNo, tuteeNo);
        return ResponseEntity.ok().body(keywords);
    }

    @Operation(
            summary = "튜터 매칭 수락/거절 API",
            description = "튜티의 매칭 요청을 수락 하거나 거절합니다<br>" +
                    "OK: 매칭 수락, NO: 매칭 거절 / WAIT: 대기상태(매칭 요청시 기본값)"
    )
    @PostMapping("/update-status")
    public ResponseEntity<String> updateMentorship(@RequestBody MentorshipAcceptRequest request) {

        return ResponseEntity.ok().body(mentorshipService.updateMentorship(request));
    }

    @Operation(
            summary = "후배 삭제 API",
            description = "멘토링을 진행하고 있는 후배와의 매칭을 삭제합니다."
    )
    @DeleteMapping("/{mentorshipNo}")
    public ResponseEntity<ResponseDTO<Void>> deleteTuteeByMentorship(@PathVariable(name = "mentorshipNo") Long mentorshipNo) {
        mentorshipService.deleteTuteeByMentorship(mentorshipNo);
        return ResponseEntity.ok().body(
                ResponseDTO.<Void>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("후배 삭제 완료")
                        .build()
        );
    }
}