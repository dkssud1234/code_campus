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
    private Long classNo; // 시퀀스번호
    @ManyToOne
    @JoinColumn(name = "subject_code")
    private Subject subjectCode; // 과목코드 (MATH, ENG, ...)
    private String classArea; // 수업 지역 코드 (데이터량이 많아 일단 서비스는 수도권부터 베타서비스 실시)
    private String classDate; // 수업 가능한 날짜 (MON, TUE, WEN ...)
    private String classIntro; // 수업 소개 (자유형식)
    private Double classPay; // 수업료
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no") // User 엔티티의 기본 키를 참조하는 외래 키
    private User userNo; // 수업 만든 유저 번호
}


