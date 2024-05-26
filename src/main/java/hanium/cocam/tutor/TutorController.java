package hanium.cocam.tutor;

import hanium.cocam.tutor.dto.TutorListResponse;
import hanium.cocam.tutor.dto.TutorSearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutor")
@Slf4j
public class TutorController {

    private final TutorService tutorService;

    @GetMapping("/find")
    public ResponseEntity<List<TutorListResponse>> findTutor(@RequestBody(required = false) TutorSearchCond tutorSearchCond) {
        List<TutorListResponse> findTutors;

        if(tutorSearchCond != null) {
            findTutors = tutorService.findTutor(tutorSearchCond);
        } else {
            findTutors = tutorService.findTutor();
        }

        return ResponseEntity.ok().body(findTutors);
    }
}
