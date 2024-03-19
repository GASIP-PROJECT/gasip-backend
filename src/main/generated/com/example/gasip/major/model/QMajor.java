package com.example.gasip.major.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMajor is a Querydsl query type for Major
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMajor extends EntityPathBase<Major> {

    private static final long serialVersionUID = -1848124352L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMajor major = new QMajor("major");

    public final com.example.gasip.college.model.QCollege college;

    public final NumberPath<Long> majorId = createNumber("majorId", Long.class);

    public final StringPath majorName = createString("majorName");

    public QMajor(String variable) {
        this(Major.class, forVariable(variable), INITS);
    }

    public QMajor(Path<? extends Major> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMajor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMajor(PathMetadata metadata, PathInits inits) {
        this(Major.class, metadata, inits);
    }

    public QMajor(Class<? extends Major> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.college = inits.isInitialized("college") ? new com.example.gasip.college.model.QCollege(forProperty("college")) : null;
    }

}

