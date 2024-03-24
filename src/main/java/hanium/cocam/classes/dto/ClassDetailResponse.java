package hanium.cocam.classes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassDetailResponse {
    private String mentorProfile; // 멘토 프로필 사진
    private String userNickName; // 유저닉네임
    private String userSex; // 유저 성별
    private String mentorUniv; // 멘토 소속 대학
    private String mentorClassNum; // 멘토 학번(20학번, 21학번 ...)
    private String mentorMajor; // 멘토 학과
    private String mentorMbti; // 멘토 Mbti
    private String mentorIntro; // 멘토 소개글 (자유 형식)
    private String subjectName; // 과목 이름
    private String classArea; // 수업 지역 코드(데이터량이 많아 일단 서비스는 수도권부터 베타서비스 실시)
    private String classDate; // 수업 가능한 날짜(MON, TUE, WEN ...)
    private String classIntro; // 수업 소개(자유형식)
    private Double classPay; // 수업료(만원단위)
}
