package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.mentorship.dto.MentorshipAcceptRequest;
import hanium.cocam.domain.mentorship.dto.MentorshipRequest;
import hanium.cocam.domain.user.entity.User;
import hanium.cocam.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentorshipService {

    private final MentorshipRepository mentorshipRepository;
    private final UserRepository userRepository;

    public String requestMentorship(MentorshipRequest request) {

        User tutor = userRepository.findById(request.getTutorNo()).orElseThrow(() -> new NoSuchElementException("Tutor not found User: " + request.getTutorNo()));
        User tutee = userRepository.findById(request.getTuteeNo()).orElseThrow(() -> new NoSuchElementException("Tutee not found User: " + request.getTuteeNo()));

        // Mentorship 엔티티 생성 및 저장
        Mentorship mentorship = request.toEntity(tutor, tutee);
        mentorshipRepository.save(mentorship);

        return "매칭 신청 완료";
    }

    public String updateMentorship(MentorshipAcceptRequest request) {

        Mentorship findMentorship = mentorshipRepository.findById(request.getMentorshipNo()).orElseThrow(() -> new IllegalArgumentException("not found mentorshipNo"));
        findMentorship.setMentorshipStatus(request.getStatus());
        mentorshipRepository.save(findMentorship);

        return "매칭 상태 변경 완료 status : "+request.getStatus();
    }
}

