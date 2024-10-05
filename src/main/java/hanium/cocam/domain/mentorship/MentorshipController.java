package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.mentorship.dto.MentorshipAcceptRequest;
import hanium.cocam.domain.mentorship.dto.MentorshipDetailsDTO;
import hanium.cocam.domain.mentorship.dto.MentorshipRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<MentorshipDetailsDTO>> requestMentorship(@RequestBody MentorshipRequest mentorshipRequest) {
        List<MentorshipDetailsDTO> mentorshipDetails = mentorshipService.requestMentorship(mentorshipRequest);
        return ResponseEntity.ok().body(mentorshipDetails);
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
}
