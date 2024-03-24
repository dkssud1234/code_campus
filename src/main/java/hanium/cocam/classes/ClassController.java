package hanium.cocam.classes;

import hanium.cocam.classes.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/class")
public class ClassController {

    private final ClassService classService;

    @PostMapping("/list")
    public ResponseEntity<List<ClassListResponse>> findClass(@RequestBody(required = false) ClassSearchCond mentorSearch) {
        List<ClassListResponse> findMentors;
        if (mentorSearch != null) { // 검색 조건이 있을때
            findMentors = classService.findMentor(mentorSearch);
        } else {  // 검색 조건이 없을때
            findMentors = classService.findMentor();
        }
        return ResponseEntity.ok().body(findMentors);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody AddClassRequest request, @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.addClass(request, token));
    }

    @PostMapping("/info/{classNo}")
    public ResponseEntity<ClassDetailResponse> detailClass(@PathVariable(name = "classNo") Long classNo) {
        return ResponseEntity.ok().body(classService.detailClass(classNo));
    }

    @PutMapping("/edit")
    public ResponseEntity<String> updateClass(@RequestBody UpdateClassRequest updateClassRequest, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(classService.updateClass(updateClassRequest, token));
    }
}
