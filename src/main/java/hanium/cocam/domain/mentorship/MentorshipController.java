package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.mentorship.dto.MentorshipRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentorships")
@RequiredArgsConstructor
public class MentorshipController {

    private final MentorshipService mentorshipService;

    @PostMapping("/apply")
    public ResponseEntity<String> createMentorship(@RequestBody MentorshipRequest mentorshipRequest) {
        return ResponseEntity.ok().body(mentorshipService.createMentorship(mentorshipRequest));
    }
}
