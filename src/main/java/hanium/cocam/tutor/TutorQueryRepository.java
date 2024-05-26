package hanium.cocam.tutor;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hanium.cocam.tutor.dto.TutorSearchCond;
import hanium.cocam.user.Category;
import hanium.cocam.user.User;
import hanium.cocam.user.UserSex;
import hanium.cocam.user.UserType;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static hanium.cocam.user.QUser.user;

@Repository
@Slf4j
public class TutorQueryRepository {

    private final JPAQueryFactory query;

    public TutorQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    /**
     * MentorSearchCond이 존재 하지 않을 때는 모두 조회
     * @author jskim
     * @version 1.0
     * @return
     */
    public List<User> findAll() {
        return query.select(user)
                .from(user)
                .where(user.userType.eq(UserType.TUTOR))
                .fetch();
    }

    /**
     * MentorSearchCond이 존재할 때 필터 검색 처리
     * @author jskim
     * @version 1.0
     * @param cond
     * @return
     */
    public List<User> findAll(TutorSearchCond cond) {
        OrderSpecifier[] orderSpecifier = createOrderSpecifiers(cond);

        return query.select(user)
                .from(user)
                .orderBy(orderSpecifier)
                .where(
                        user.userType.eq(UserType.TUTOR),
                        eqUserSex(cond.getUserSex()),
                        likeClassArea(cond.getClassArea()),
                        likeCategory(cond.getCategory()),
                        likeLevel(cond.getLevel())
                )
                .fetch();
    }

    private OrderSpecifier[] createOrderSpecifiers(TutorSearchCond cond) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        String orderCondition = cond.getOrderCondition();

        if(Objects.isNull(orderCondition)){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, user.tutorLikes));
        } else if(orderCondition.equals("NEW")){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, user.createdAt));
        } else if(orderCondition.equals("POP")){
            orderSpecifiers.add(new OrderSpecifier(Order.DESC, user.tutorLikes));
        }
        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }

    private BooleanExpression eqUserSex(UserSex userSex) {
        if (userSex != null && !userSex.equals("")) {
            return user.userSex.eq(userSex);
        }
        return null;
    }

    private BooleanExpression likeLevel(String level) {
        if (StringUtils.hasText(level)) {
            return user.level.like("%" + level + "%");
        }
        return null;
    }

    private BooleanExpression likeCategory(Category category) {
        if (category != null && !category.equals("")) {
            return user.category.eq(category);
        }
        return null;
    }

    private BooleanExpression likeClassArea(String classArea) {
        if (StringUtils.hasText(classArea)) {
            return user.classArea.like("%" + classArea + "%");
        }
        return null;
    }
}