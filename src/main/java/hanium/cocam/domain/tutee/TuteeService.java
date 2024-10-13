package hanium.cocam.domain.tutee;

import hanium.cocam.domain.mentorship.Mentorship;
import hanium.cocam.domain.mentorship.MentorshipRepository;
import hanium.cocam.domain.tutee.dto.*;
import hanium.cocam.domain.user.UserRepository;
import hanium.cocam.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TuteeService {

    private final UserRepository userRepository;
    private final MentorshipRepository mentorshipRepository;

    public TuteeMyPageResponse getMyPageDetail(Long tuteeNo) {
        User tutee = userRepository.findById(tuteeNo)
                .orElseThrow(() -> new NoSuchElementException("해당 사용자를 찾을 수 없습니다: " + tuteeNo));

        TuteeDetailResponse tuteeDetail = TuteeDetailResponse.builder()
                .tuteeProfileImg(tutee.getProfile().getTuteeProfileImg())
                .name(tutee.getUserName())
                .userType(tutee.getUserType())
                .build();

        List<RequestedMentorshipListResponse> requestedList = mentorshipRepository.findByTuteeAndMentorshipStatus(tutee, "WAIT")
                .stream()
                .map(mentorship -> new RequestedMentorshipListResponse(
                        mentorship.getMentorshipNo(),
                        mentorship.getTutor().getUserNo(),
                        mentorship.getTutor().getUserName(),
                        mentorship.getMentorshipDay() + " " + mentorship.getMentorshipTime(),
                        mentorship.getNote()
                ))
                .collect(Collectors.toList());

        List<MyTutorListResponse> ongoingList = mentorshipRepository.findByTuteeAndMentorshipStatus(tutee, "OK")
                .stream()
                .map(mentorship -> new MyTutorListResponse(
                        mentorship.getMentorshipNo(),
                        mentorship.getTutor().getUserNo(),
                        mentorship.getTutor().getUserName(),
                        mentorship.getMentorshipDay() + " " + mentorship.getMentorshipTime()
                ))
                .collect(Collectors.toList());

        return TuteeMyPageResponse.builder()
                .tuteeDetailResponse(tuteeDetail)
                .requestedMentorshipList(requestedList)
                .ongoingMentorshipList(ongoingList)
                .build();
    }

    public TutorDetailResponse getMyTutorDetail(Long mentorshipNo) {
        // mentorshipNo에 해당하는 멘토십 조회
        Mentorship mentorship = mentorshipRepository.findById(mentorshipNo)
                .orElseThrow(() -> new NoSuchElementException("해당 멘토십 번호에 대한 정보를 찾을 수 없습니다: " + mentorshipNo));

        // 멘토십의 튜터 정보 가져오기
        User tutor = mentorship.getTutor();

        // TutorDetailResponse 생성 및 반환
        return TutorDetailResponse.builder()
                .tutorNo(tutor.getUserNo())
                .name(tutor.getUserName())
                .classLevel(tutor.getProfile().getLevel())
                .keywordList(tutor.getProfile().getKeywordArray()) // 키워드 배열
                .mentorshipDay(mentorship.getMentorshipDay().split(","))       // 멘토십 요일 배열
                .mentorshipTime(mentorship.getMentorshipTime())
                .tutorIntro(tutor.getProfile().getTutorIntro())
                .build();
    }
}