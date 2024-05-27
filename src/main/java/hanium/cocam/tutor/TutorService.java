package hanium.cocam.tutor;

import hanium.cocam.tutor.dto.TutorListResponse;
import hanium.cocam.tutor.dto.TutorSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorService {

    private final TutorQueryRepository tutorQueryRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    public List<TutorListResponse> findTutor() {
        List<TutorListResponse> findTutors = tutorQueryRepository.findAll().stream()
            .map(user -> new TutorListResponse(user, user.getProfile())).toList();
        return findTutors;
    }

    public List<TutorListResponse> findTutor(TutorSearchCond tutorSearchCond) {
        List<TutorListResponse> findTutors = tutorQueryRepository.findAll(tutorSearchCond).stream()
            .map(user -> new TutorListResponse(user, user.getProfile())).toList();
        return findTutors;
    }
}
