package hanium.cocam.classes;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hanium.cocam.classes.dto.ClassListResponse;
import hanium.cocam.classes.dto.ClassSearchCond;
import hanium.cocam.user.User;
import hanium.cocam.user.UserType;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.core.types.Projections.constructor;
import static hanium.cocam.classes.QClasses.classes;
import static hanium.cocam.user.QUser.user;

@Repository
@Slf4j
public class ClassQueryRepository {

    private final JPAQueryFactory query;

    public ClassQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    /**
     * ClassSearchCond 존재 하지 않을 때는 모두 조회
     * @return
     */
    public List<ClassListResponse> findAll() {

        // Classes 객체 리스트 조회
        List<Classes> classesList = query.selectFrom(classes)
                .join(classes.userNo, user)
                .fetch();

        // Classes 객체 리스트를 ClassListResponse 객체 리스트로 변환
        return classesList.stream()
                .map(c -> new ClassListResponse(c.getUserNo(), c))
                .collect(Collectors.toList());
    }

    /**
     * ClassSearchCond 존재할 때 동적 쿼리
     * @param cond
     * @return
     */
    public List<ClassListResponse> findAll(ClassSearchCond cond) {
        return query.select(constructor(ClassListResponse.class,
                        user.userName,
                        classes.classType,
                        classes.classTitle,
                        classes.classLevel))
                .from(classes)
                .join(classes.userNo, user)
                .where(
                        user.userType.eq(UserType.TUTOR),
                        eqUserSex(cond.getUserSex()),
                        likeTutorUniv(cond.getTutorUniv()),
                        likeTutorMajor(cond.getTutorMajor()),
                        likeSubjectName(cond.getSubjectName()),
                        likeClassLevel(cond.getClassLevel()),
                        likeClassTitle(cond.getClassTitle()),
                        likeClassType(cond.getClassType()),
                        likeClassArea(cond.getClassArea())
                )
                .fetch();
    }

    public Tuple detailClass(Long classNo) {
        Tuple tuple = query.select(
                        classes.classArea,
                        classes.classDate,
                        classes.classIntro,
                        classes.subjectName,
                        user.userSex,
                        user.tutorClassNum,
                        user.tutorProfile,
                        user.tutorIntro,
                        user.tutorUniv,
                        user.userNickName,
                        user.tutorMajor
                )
                .from(classes)
                .join(classes.userNo, user)
                .where(classes.classNo.eq(classNo))
                .fetchOne();

        if (tuple == null) {
            // 클래스가 없는 경우 예외 처리
            throw new RuntimeException("해당 선배의 강의가 존재 하지 않습니다.");
        }

        return tuple;
    }

    private BooleanExpression eqUserSex(String userSex) {
        if (StringUtils.hasText(userSex)) {
            return user.userSex.eq(userSex);
        }
        return null;
    }

    private BooleanExpression likeTutorUniv(String tutorUniv) {
        if (StringUtils.hasText(tutorUniv)) {
            return user.tutorUniv.like("%" + tutorUniv + "%");
        }
        return null;
    }

    private BooleanExpression likeTutorMajor(String tutorMajor) {
        if (StringUtils.hasText(tutorMajor)) {
            return user.tutorMajor.like("%" + tutorMajor + "%");
        }
        return null;
    }

    private BooleanExpression likeSubjectName(String subjectName) {
        if (StringUtils.hasText(subjectName)) {
            return classes.subjectName.eq(subjectName);
        }
        return null;
    }

    private BooleanExpression likeClassArea(String classArea) {
        if (StringUtils.hasText(classArea)) {
            return classes.classArea.like("%" + classArea + "%");
        }
        return null;
    }

    private BooleanExpression likeClassLevel(String classLevel) {
        if (StringUtils.hasText(classLevel)) {
            return classes.classLevel.like("%" + classLevel + "%");
        }
        return null;
    }

    private BooleanExpression likeClassTitle(String classTitle) {
        if (StringUtils.hasText(classTitle)) {
            return classes.classTitle.like("%" + classTitle + "%");
        }
        return null;
    }

    private BooleanExpression likeClassType(ClassType classType) {
        if (classType != null) {
            return classes.classType.eq(classType);
        }
        return null;
    }
}
