package hanium.cocam.mentorship;

import hanium.cocam.mentorship.dto.MentorshipRequest;
import hanium.cocam.user.User;
import hanium.cocam.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentorshipService {

    private final MentorshipRepository mentorshipRepository;
    private final UserRepository userRepository;

    public String createMentorship(MentorshipRequest request) {

        User tutor = userRepository.findById(request.getTutorNo())
                .orElseThrow(() -> new NoSuchElementException("Tutor not found User: " + request.getTutorNo()));
        User tutee = userRepository.findById(request.getTuteeNo())
                .orElseThrow(() -> new NoSuchElementException("Tutee not found User: " + request.getTuteeNo()));

        // Mentorship 엔티티 생성 및 저장
        Mentorship mentorship = request.toEntity(tutor, tutee);
        mentorshipRepository.save(mentorship);

        return "신청 완료";
    }
}

