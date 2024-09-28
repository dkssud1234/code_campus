package hanium.cocam.domain.tutor;

import hanium.cocam.domain.mentorship.MentorshipRepository;
import hanium.cocam.domain.tutor.dto.*;
import hanium.cocam.domain.user.UserRepository;
import hanium.cocam.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorService {

    private final TutorQueryRepository tutorQueryRepository;
    private final UserRepository userRepository;
    private final MentorshipRepository mentorshipRepository;

    public List<TutorListResponse> findTutor() {
        List<TutorListResponse> findTutors = tutorQueryRepository.findAll()
                .stream()
                .map(user -> new TutorListResponse(user, user.getProfile())).toList();
        return findTutors;
    }

    public List<TutorListResponse> findTutor(TutorSearchCond tutorSearchCond) {
        List<TutorListResponse> findTutors = tutorQueryRepository.findAll(tutorSearchCond)
                .stream()
                .map(user -> new TutorListResponse(user, user.getProfile())).toList();
        return findTutors;
    }

    public TutorMyPageResponse detail(Long tutorNo) {

        User tutor = userRepository.findById(tutorNo).orElseThrow(() -> new IllegalArgumentException("not found user" + tutorNo));

        TutorDetailResponse tutorDetail = TutorDetailResponse.builder()
                .tutorProfileImg(tutor.getProfile().getTutorProfileImg())
                .name(tutor.getUserName())
                .userType(tutor.getUserType()).build();

        // 수업 진행 중인 후배 목록
        List<MyTuteeListResponse> myTuteeList = mentorshipRepository.findByTutorAndMentorshipStatus(tutor, "OK")
                .stream()
                .map(mentorship -> new MyTuteeListResponse(
                        mentorship.getTutee().getUserNo(),
                        mentorship.getTutee().getUserName(),
                        mentorship.getMentorshipDay() + " " + mentorship.getMentorshipTime()
                ))
                .collect(Collectors.toList());

        List<RequestedMentorshipListResponse> requestedList = mentorshipRepository.findByTutorAndMentorshipStatus(tutor, "WAIT")
                .stream()
                .map(mentorship -> new RequestedMentorshipListResponse(
                        mentorship.getTutee().getUserNo(),
                        mentorship.getTutee().getUserName(),
                        mentorship.getMentorshipDay() + " " + mentorship.getMentorshipTime(),
                        mentorship.getNote()
                ))
                .collect(Collectors.toList());

        return TutorMyPageResponse.builder()
                .tutorDetailResponse(tutorDetail)
                .myTuteeList(myTuteeList)
                .requestedList(requestedList)
                .build();
    }
}
