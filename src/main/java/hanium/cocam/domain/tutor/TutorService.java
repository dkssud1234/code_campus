package hanium.cocam.domain.tutor;

import hanium.cocam.domain.mentorship.Mentorship;
import hanium.cocam.domain.mentorship.MentorshipRepository;
import hanium.cocam.domain.tutor.dto.*;
import hanium.cocam.domain.user.UserRepository;
import hanium.cocam.domain.user.entity.Profile;
import hanium.cocam.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
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

    public TutorProfileResponse profileDetail(Long tutorNo) {
        User tutor = userRepository.findById(tutorNo).orElseThrow(() -> new NoSuchElementException("not found user" + tutorNo));

        TutorProfileResponse tutorProfile = TutorProfileResponse.builder()
                .tutorProfileImg(tutor.getProfile().getTutorProfileImg())
                .keyword(tutor.getProfile().getKeyword())
                .name(tutor.getUserName())
                .classArea(tutor.getProfile().getClassArea())
                .classType(tutor.getProfile().getClassType())
                .school(tutor.getProfile().getSchool())
                .tutorMajor(tutor.getProfile().getTutorMajor())
                .tutorIntro(tutor.getProfile().getTutorIntro())
                .chatLink(tutor.getProfile().getChatLink())
                .portLink(tutor.getProfile().getPortLink())
                .build();

        return tutorProfile;
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

    public TuteeDetailResponse getTuteeDetailByMentorship(Long mentorshipNo) {
        // mentorshipNo에 해당하는 멘토십 조회
        Mentorship mentorship = mentorshipRepository.findById(mentorshipNo)
                .orElseThrow(() -> new NoSuchElementException("해당 멘토십 번호에 대한 정보를 찾을 수 없습니다: " + mentorshipNo));

        // 멘토십의 튜티 정보 가져오기
        User tutee = mentorship.getTutee();

        // TuteeDetailResponse 생성 및 반환
        return TuteeDetailResponse.builder()
                .tuteeNo(tutee.getUserNo())
                .name(tutee.getUserName())
                .keywordList(tutee.getProfile().getKeywordArray()) // 키워드 배열
                .mentorshipDay(mentorship.getMentorshipDay().split(","))       // 멘토십 요일 배열
                .mentorshipTime(mentorship.getMentorshipTime()) // 선호 시간은 리스트
                .note(mentorship.getNote())                        // 노트
                .build();
    }

    public void deleteTuteeByMentorship(Long mentorshipNo) {
        Mentorship mentorship = mentorshipRepository.findById(mentorshipNo)
                .orElseThrow(() -> new NoSuchElementException("해당 멘토십 번호에 대한 정보를 찾을 수 없습니다: " + mentorshipNo));

        mentorshipRepository.delete(mentorship);
    }
}
