package hanium.cocam.classes;

import com.querydsl.core.Tuple;
import hanium.cocam.classes.dto.*;
import hanium.cocam.jwt.JwtUtil;
import hanium.cocam.user.User;
import hanium.cocam.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static hanium.cocam.classes.QClasses.*;
import static hanium.cocam.classes.QSubject.*;
import static hanium.cocam.user.QUser.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassService {

    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final ClassQueryRepository classQueryRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    public List<ClassListResponse> findMentor() {
        List<ClassListResponse> mentors = classQueryRepository.findAll().stream().map(ClassListResponse::new).toList();
        return mentors;
    }

    public List<ClassListResponse> findMentor(ClassSearchCond mentorSearch) {
        List<ClassListResponse> mentors = classQueryRepository.findAll(mentorSearch).stream().map(ClassListResponse::new).toList();
        return mentors;
    }

    public String addClass(AddClassRequest request, String token) {
        String accessToken = token.split(" ")[1];
        Long userNo = JwtUtil.getUserNo(accessToken, secretKey); // 해당 사용자의 토큰값으로 userNo (유저 고유 번호)를 가져옴 검증 과정
        try {
            List<String> subjectCodeList = request.getSubjectCode().stream().toList();
            User user = userRepository.findById(userNo).orElseThrow(() -> new RuntimeException("User not found"));
            if (!user.getUserType().toString().equals("MENTOR")) {
                return "멘토 회원만 강의 등록이 가능합니다.";
            }
            for (String subjectCode : subjectCodeList) {
                Subject subject = subjectRepository.findById(subjectCode).orElseThrow(() -> new RuntimeException("Subject code not found"));
                classRepository.save(request.toEntity(subject, user));
            }
            return "강의 등록이 완료 되었습니다.";
        } catch (Exception e) {
            return "강의 등록 중 오류가 발생했습니다.";
        }
    }

    public ClassDetailResponse detailClass(Long classNo) {
        Tuple tuple = classQueryRepository.detailClass(classNo);

        return ClassDetailResponse.builder()
                .classArea(tuple.get(classes.classArea))
                .classDate(tuple.get(classes.classDate))
                .classIntro(tuple.get(classes.classIntro))
                .subjectName(tuple.get(subject.subjectName))
                .classPay(tuple.get(classes.classPay))
                .userSex(tuple.get(user.userSex))
                .mentorClassNum(tuple.get(user.mentorClassNum))
                .mentorProfile(tuple.get(user.mentorProfile))
                .mentorIntro(tuple.get(user.mentorIntro))
                .mentorUniv(tuple.get(user.mentorUniv))
                .userNickName(tuple.get(user.userNickName))
                .mentorMajor(tuple.get(user.mentorMajor))
                .mentorMbti(tuple.get(user.mentorMbti))
                .build();
    }

    public String updateClass(UpdateClassRequest updateClassRequest, String token) {
        String msg = "";
        try {
            String accessToken = token.split(" ")[1];
            Long userNo = JwtUtil.getUserNo(accessToken, secretKey);

            List<Classes> findClassList = classRepository.findByUserNo_UserNo(userNo).stream().toList();

            for (Classes findClass : findClassList) {
                findClass.setClassArea(updateClassRequest.getClassArea());
                findClass.setClassDate(updateClassRequest.getClassDate());
                findClass.setSubjectCode((Subject) updateClassRequest.getSubjectCode());
                findClass.setClassIntro(updateClassRequest.getClassIntro());
                findClass.setClassPay(updateClassRequest.getClassPay());
                classRepository.save(findClass);
            }
            msg = "강의 수정이 완료되었습니다.";

        } catch (Exception e) {
           msg = e.getMessage();
        }
        return msg;
    }
}
