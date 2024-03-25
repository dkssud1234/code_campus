package hanium.cocam.classes.dto;

import hanium.cocam.classes.ClassType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassDetailResponse {
    private String classTitle; // 강의 제목
    private String userName; // 유저 이름
    private String tutorUniv; // 멘토 소속 대학
    private String tutorClassNum; // 멘토 학번(20학번, 21학번 ...)
    private String tutorMajor; // 멘토 학과
    private String tutorIntro; // 멘토 소개글 (자유 형식)
    private String subjectName; // 과목 이름
    private ClassType classType; // 수업 방식(ON, OFF, BOTH)
    private String classArea; // 수업 지역 코드(데이터량이 많아 일단 서비스는 수도권부터 베타서비스 실시)
    private String classDate; // 수업 가능한 날짜(MON, TUE, WEN ...)
    private String classIntro; // 수업 소개(자유형식)
}
