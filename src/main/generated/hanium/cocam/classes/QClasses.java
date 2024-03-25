package hanium.cocam.classes;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClasses is a Querydsl query type for Classes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClasses extends EntityPathBase<Classes> {

    private static final long serialVersionUID = -1348988975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClasses classes = new QClasses("classes");

    public final StringPath chatLink = createString("chatLink");

    public final StringPath classArea = createString("classArea");

    public final StringPath classDate = createString("classDate");

    public final StringPath classIntro = createString("classIntro");

    public final StringPath classLevel = createString("classLevel");

    public final NumberPath<Long> classNo = createNumber("classNo", Long.class);

    public final StringPath classTitle = createString("classTitle");

    public final EnumPath<ClassType> classType = createEnum("classType", ClassType.class);

    public final StringPath subjectName = createString("subjectName");

    public final hanium.cocam.user.QUser userNo;

    public QClasses(String variable) {
        this(Classes.class, forVariable(variable), INITS);
    }

    public QClasses(Path<? extends Classes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClasses(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClasses(PathMetadata metadata, PathInits inits) {
        this(Classes.class, metadata, inits);
    }

    public QClasses(Class<? extends Classes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userNo = inits.isInitialized("userNo") ? new hanium.cocam.user.QUser(forProperty("userNo")) : null;
    }

}

