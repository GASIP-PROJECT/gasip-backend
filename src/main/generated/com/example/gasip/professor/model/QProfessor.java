package com.example.gasip.professor.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfessor is a Querydsl query type for Professor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfessor extends EntityPathBase<Professor> {

    private static final long serialVersionUID = -742031956L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfessor professor = new QProfessor("professor");

    public final com.example.gasip.major.model.QMajor major;

    public final NumberPath<Long> profId = createNumber("profId", Long.class);

    public final StringPath profName = createString("profName");

    public QProfessor(String variable) {
        this(Professor.class, forVariable(variable), INITS);
    }

    public QProfessor(Path<? extends Professor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfessor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfessor(PathMetadata metadata, PathInits inits) {
        this(Professor.class, metadata, inits);
    }

    public QProfessor(Class<? extends Professor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.major = inits.isInitialized("major") ? new com.example.gasip.major.model.QMajor(forProperty("major"), inits.get("major")) : null;
    }

}

