package hanium.cocam.tutor;

import hanium.cocam.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "TB_TUTORS")
@Builder
public class Tutor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutorNo; // 유저 번호
    private Long userNo; // 유저 번호
    private String tutorId;
    private String password;
    private String tutorName;
    private String tutorEmail;
    private String tutorPhone;
    private String tutorProfile; // 유저 프로필 (기본사진)
    private String tutorUniv; // 선배 재학 학교
    private String tutorMajor; // 선배 전공
    private String classLang; // 후배 배울 언어 (리액트, 스프링, 자바, ...)
    private String classLevel; // 후배 레벨 (입문, 초급, 중급이상)
    private String tutorClassNum; // 선배 학번
    private String tutorIntro; // 선배 소개글
}
