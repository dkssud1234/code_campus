package hanium.cocam.classes;

import hanium.cocam.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "TB_CLASS")
@Builder
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classNo; // 강의 번호
    private String classThumb; // 강의 썸네일 이미지
    private String classTitle; // 강의 제목
    private String classLevel; // 강의 난이도(입문, 초급, 중급이상)
    private String subjectName; // 강의에 사용되는 과목명(C, 자바, 자바스크립트, 리액트 ...)
    private String classArea; // (오프라인 한정) 수업 지역 코드
    private String classDate; // 수업 가능한 날짜 (MON, TUE, WEN ...)
    private String classIntro; // 수업 소개 (자유형식)
    @Enumerated(EnumType.STRING) // String 으로 저장
    private ClassType classType; // 수업 방식(ON, OFF, BOTH)
    private String chatLink; // 오픈채팅방 링크
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no") // User 엔티티의 기본 키를 참조하는 외래 키
    private User userNo; // 수업 만든 유저 번호
}


