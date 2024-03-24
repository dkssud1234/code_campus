package hanium.cocam.classes;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hanium.cocam.classes.dto.ClassSearchCond;
import hanium.cocam.user.User;
import hanium.cocam.user.UserType;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static hanium.cocam.classes.QClasses.classes;
import static hanium.cocam.classes.QSubject.subject;
import static hanium.cocam.user.QUser.user;

@Repository
@Slf4j
public class ClassQueryRepository {

    private final JPAQueryFactory query;

    public ClassQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    /**
     * MentorSearchCond이 존재 하지 않을 때는 모두 조회
     * @return
     */
    public List<User> findAll() {
        return query.select(user)
                .from(user)
                .join(classes).on(user.eq(classes.userNo))
                .where(user.userType.eq(UserType.MENTOR))
                .fetch();
    }

    /**
     * MentorSearchCond이 존재할 때 동적쿼리
     * @param cond
     * @return
     */
    public List<User> findAll(ClassSearchCond cond) {
        return query.select(user)
                .from(user)
                .join(classes).on(user.eq(classes.userNo))
                .where(
                        user.userType.eq(UserType.MENTOR),
                        eqUserSex(cond.getUserSex()),
                        likeMentorUniv(cond.getMentorUniv()),
                        likeMentorMajor(cond.getMentorMajor()),
                        likeSubjectName(cond.getSubjectName()),
                        likeClassArea(cond.getClassArea())
                )
                .fetch();
    }

    public Tuple detailClass(Long classNo) {
        Tuple tuple = query.select(
                        classes.classArea,
                        classes.classDate,
                        classes.classIntro,
                        subject.subjectName,
                        user.userSex,
                        user.mentorClassNum,
                        classes.classPay,
                        user.mentorProfile,
                        user.mentorIntro,
                        user.mentorUniv,
                        user.userNickName,
                        user.mentorMajor,
                        user.mentorMbti
                )
                .from(classes)
                .join(user).on(user.eq(classes.userNo))
                .join(subject).on(subject.eq(classes.subjectCode))
                .where(classes.classNo.eq(classNo))
                .fetchOne();

        if (tuple == null) {
            // 클래스가 없는 경우 예외 처리
            throw new RuntimeException("해당 사용자의 강의가 존재 하지 않습니다.");
        }

        return tuple;
    }

    private BooleanExpression eqUserSex(String userSex) {
        if (StringUtils.hasText(userSex)) {
            return user.userSex.eq(userSex);
        }
        return null;
    }

    private BooleanExpression likeMentorUniv(String mentorUniv) {
        if (StringUtils.hasText(mentorUniv)) {
            return user.mentorUniv.like("%" + mentorUniv + "%");
        }
        return null;
    }

    private BooleanExpression likeMentorMajor(String mentorMajor) {
        if (StringUtils.hasText(mentorMajor)) {
            return user.mentorMajor.like("%" + mentorMajor + "%");
        }
        return null;
    }

    private BooleanExpression likeSubjectName(String subjectName) {
        if (StringUtils.hasText(subjectName)) {
            return classes.subjectCode.subjectName.eq(subjectName);
        }
        return null;
    }

    private BooleanExpression likeClassArea(String classArea) {
        if (StringUtils.hasText(classArea)) {
            return classes.classArea.like("%" + classArea + "%");
        }
        return null;
    }
}
