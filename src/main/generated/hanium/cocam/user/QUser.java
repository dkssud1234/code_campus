package hanium.cocam.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 939080705L;

    public static final QUser user = new QUser("user");

    public final StringPath mentorClassNum = createString("mentorClassNum");

    public final StringPath mentorIntro = createString("mentorIntro");

    public final StringPath mentorMajor = createString("mentorMajor");

    public final StringPath mentorMbti = createString("mentorMbti");

    public final StringPath mentorProfile = createString("mentorProfile");

    public final StringPath mentorUniv = createString("mentorUniv");

    public final StringPath password = createString("password");

    public final NumberPath<Integer> userAge = createNumber("userAge", Integer.class);

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userNickName = createString("userNickName");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath userPhone = createString("userPhone");

    public final StringPath userSex = createString("userSex");

    public final EnumPath<UserType> userType = createEnum("userType", UserType.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

