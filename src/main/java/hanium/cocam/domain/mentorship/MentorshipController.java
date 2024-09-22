package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.mentorship.dto.MentorshipRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentorships")
@RequiredArgsConstructor
public class MentorshipController {

    private final MentorshipService mentorshipService;

    @Operation(
            summary = "튜터 매칭 API",
            description = "튜터와 튜티의 id를 받아 매칭합니다 <br>" +
                    "튜터의 id는 해당 유저의 id를 가져오고, 튜터의 id는 로그인할 때 리턴한 유저의 id를 사용합니다."
    )
    @PostMapping("/apply")
    public ResponseEntity<String> createMentorship(@RequestBody MentorshipRequest mentorshipRequest) {
        return ResponseEntity.ok().body(mentorshipService.createMentorship(mentorshipRequest));
    }
}
