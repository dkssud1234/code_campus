//package hanium.cocam.classes;
//
//import com.querydsl.core.Tuple;
//import hanium.cocam.classes.dto.*;
//import hanium.cocam.jwt.JwtUtil;
//import hanium.cocam.user.User;
//import hanium.cocam.user.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static hanium.cocam.classes.QClasses.*;
//import static hanium.cocam.user.QUser.*;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ClassService {
//
////    private final ClassRepository classRepository;
//    private final UserRepository userRepository;
////    private final ClassQueryRepository classQueryRepository;
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
////    public List<ClassListResponse> findClass() {
////        List<ClassListResponse> findClasses = classQueryRepository.findAll();
////        return findClasses;
////    }
////
////    public List<ClassListResponse> findClass(ClassSearchCond mentorSearch) {
////        List<ClassListResponse> findClasses = classQueryRepository.findAll(mentorSearch);
////        return findClasses;
////    }
//
//    public String addClass(AddClassRequest request, String token) {
//        String accessToken = token.split(" ")[1];
//        Long userNo = JwtUtil.getUserNo(accessToken, secretKey); // 해당 사용자의 토큰값으로 userNo (유저 고유 번호)를 가져옴 검증 과정
//        try {
//            User user = userRepository.findById(userNo).orElseThrow(() -> new RuntimeException("User not found"));
//            if (!user.getUserType().toString().equals("TUTOR")) {
//                return "선배 회원만 강의 등록이 가능합니다.";
//            }
//            classRepository.save(request.toEntity(user));
//
//            return "강의 등록이 완료 되었습니다.";
//        } catch (Exception e) {
//            return "강의 등록 중 오류가 발생했습니다.";
//        }
//    }
//
////    public ClassDetailResponse detailClass(Long classNo) {
////        Tuple tuple = classQueryRepository.detailClass(classNo);
////
////        return ClassDetailResponse.builder()
////                .classArea(tuple.get(classes.classArea))
////                .classDate(tuple.get(classes.classDate))
////                .classIntro(tuple.get(classes.classIntro))
////                .subjectName(tuple.get(classes.subjectName))
////                .tutorClassNum(tuple.get(user.tutorClassNum))
////                .tutorIntro(tuple.get(user.tutorIntro))
////                .tutorUniv(tuple.get(user.tutorUniv))
////                .tutorMajor(tuple.get(user.tutorMajor))
////                .build();
////    }
//}
