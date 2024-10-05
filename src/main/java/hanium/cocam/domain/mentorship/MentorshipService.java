package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.mentorship.dto.MentorshipAcceptRequest;
import hanium.cocam.domain.mentorship.dto.MentorshipDetailsDTO;
import hanium.cocam.domain.mentorship.dto.MentorshipRequest;
import hanium.cocam.domain.user.entity.User;
import hanium.cocam.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentorshipService {

    private final MentorshipRepository mentorshipRepository;
    private final UserRepository userRepository;

    public List<MentorshipDetailsDTO> requestMentorship(MentorshipRequest request) {
        User tutor = userRepository.findById(request.getTutorNo())
                .orElseThrow(() -> new NoSuchElementException("Tutor not found User: " + request.getTutorNo()));
        User tutee = userRepository.findById(request.getTuteeNo())
                .orElseThrow(() -> new NoSuchElementException("Tutee not found User: " + request.getTuteeNo()));

        // Mentorship 엔티티 리스트를 생성 및 저장
        List<MentorshipDetailsDTO> mentorshipDetailsList = new ArrayList<>();

        for (String day : request.getMentorshipDay()) {
            for (String time : request.getMentorshipTime()) {
                Mentorship mentorship = Mentorship.builder()
                        .tutor(tutor)
                        .tutee(tutee)
                        .mentorshipDay(day)
                        .mentorshipTime(time)
                        .mentorshipStatus("WAIT")
                        .note(request.getNote())
                        .build();

                mentorshipRepository.save(mentorship);

                // DTO 생성 및 추가
                mentorshipDetailsList.add(new MentorshipDetailsDTO(
                        mentorship.getMentorshipNo(),
                        tutor.getUserNo(),
                        tutor.getProfile().getKeywordArray(),
                        tutee.getUserNo(),
                        tutee.getProfile().getKeywordArray(),
                        mentorship.getMentorshipDay(),
                        mentorship.getMentorshipTime(),
                        mentorship.getNote()
                ));
            }
        }

        return mentorshipDetailsList; // 여러 멘토십 DTO 반환
    }

    public String updateMentorship(MentorshipAcceptRequest request) {

        Mentorship findMentorship = mentorshipRepository.findById(request.getMentorshipNo()).orElseThrow(() -> new IllegalArgumentException("not found mentorshipNo"));
        findMentorship.setMentorshipStatus(request.getStatus());
        mentorshipRepository.save(findMentorship);

        return "매칭 상태 변경 완료 status : "+request.getStatus();
    }
}

