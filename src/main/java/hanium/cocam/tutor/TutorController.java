package hanium.cocam.tutor;

import hanium.cocam.dto.ResponseDTO;
import hanium.cocam.tutor.dto.TutorListResponse;
import hanium.cocam.tutor.dto.TutorSearchCond;
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

    @PostMapping("/find")
    public ResponseEntity<ResponseDTO<List<TutorListResponse>>> findTutor(@RequestBody(required = false) TutorSearchCond tutorSearchCond) {
        List<TutorListResponse> findTutors;

        if(tutorSearchCond != null) {
            findTutors = tutorService.findTutor(tutorSearchCond);
        } else {
            findTutors = tutorService.findTutor();
        }

        return ResponseEntity.ok().body(
                ResponseDTO.<List<TutorListResponse>>builder()
                        .result(true)
                        .status(HttpStatus.OK.value())
                        .message("멘토 조회 완료")
                        .data(findTutors)
                        .build()
        );
    }
}
