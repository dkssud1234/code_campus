package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.mentorship.dto.MentorshipAcceptRequest;
import hanium.cocam.domain.mentorship.dto.MentorshipKeywordsResponse;
import hanium.cocam.domain.mentorship.dto.MentorshipRequest;
import hanium.cocam.domain.user.entity.User;
import hanium.cocam.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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

    // 튜터와 튜티의 키워드 조회 로직
    public MentorshipKeywordsResponse getMentorshipKeywords(Long tutorNo, Long tuteeNo) {
        // 튜터 조회
        User tutor = userRepository.findById(tutorNo).orElseThrow(() -> new NoSuchElementException("Tutor not found User: " + tutorNo));

        // 튜티 조회
        User tutee = userRepository.findById(tuteeNo).orElseThrow(() -> new NoSuchElementException("Tutee not found User: " + tuteeNo));

        // 튜터의 키워드 가져오기
        List<String> tutorKeywords = Arrays.asList(tutor.getProfile().getKeywordArray());
        // 튜티의 키워드 가져오기
        List<String> tuteeKeywords = Arrays.asList(tutee.getProfile().getKeywordArray());

        String tutorLevel = tutor.getProfile().getLevel();
        String tuteeLevel = tutee.getProfile().getLevel();

        // 키워드 정보를 DTO로 반환
        return new MentorshipKeywordsResponse(tutorKeywords, tuteeKeywords, tutorLevel, tuteeLevel);
    }

    public String updateMentorship(MentorshipAcceptRequest request) {

        Mentorship findMentorship = mentorshipRepository.findById(request.getMentorshipNo()).orElseThrow(() -> new IllegalArgumentException("not found mentorshipNo"));
        findMentorship.setMentorshipStatus(request.getStatus());
        mentorshipRepository.save(findMentorship);

        return "매칭 상태 변경 완료 status : "+request.getStatus();
    }


    public void deleteTuteeByMentorship(Long mentorshipNo) {
        Mentorship mentorship = mentorshipRepository.findById(mentorshipNo)
                .orElseThrow(() -> new NoSuchElementException("해당 멘토십 번호에 대한 정보를 찾을 수 없습니다: " + mentorshipNo));

        mentorshipRepository.delete(mentorship);
    }
}

