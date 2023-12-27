package com.example.gasip.professordetail.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfessorDetail is a Querydsl query type for ProfessorDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfessorDetail extends EntityPathBase<ProfessorDetail> {

    private static final long serialVersionUID = 1264749230L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfessorDetail professorDetail = new QProfessorDetail("professorDetail");

    public final com.example.gasip.major.model.QMajor major;

    public final NumberPath<Long> profId = createNumber("profId", Long.class);

    public final StringPath profName = createString("profName");

    public QProfessorDetail(String variable) {
        this(ProfessorDetail.class, forVariable(variable), INITS);
    }

    public QProfessorDetail(Path<? extends ProfessorDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfessorDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfessorDetail(PathMetadata metadata, PathInits inits) {
        this(ProfessorDetail.class, metadata, inits);
    }

    public QProfessorDetail(Class<? extends ProfessorDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.major = inits.isInitialized("major") ? new com.example.gasip.major.model.QMajor(forProperty("major"), inits.get("major")) : null;
    }

}

