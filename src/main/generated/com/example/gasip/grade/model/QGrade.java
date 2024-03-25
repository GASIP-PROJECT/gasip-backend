package com.example.gasip.grade.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGrade is a Querydsl query type for Grade
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGrade extends EntityPathBase<Grade> {

    private static final long serialVersionUID = -1369853060L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGrade grade = new QGrade("grade");

    public final com.example.gasip.global.entity.QBaseTimeEntity _super = new com.example.gasip.global.entity.QBaseTimeEntity(this);

    public final NumberPath<Long> gradeId = createNumber("gradeId", Long.class);

    public final NumberPath<Integer> gradepoint = createNumber("gradepoint", Integer.class);

    public final com.example.gasip.member.model.QMember member;

    public final com.example.gasip.professor.model.QProfessor professor;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QGrade(String variable) {
        this(Grade.class, forVariable(variable), INITS);
    }

    public QGrade(Path<? extends Grade> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGrade(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGrade(PathMetadata metadata, PathInits inits) {
        this(Grade.class, metadata, inits);
    }

    public QGrade(Class<? extends Grade> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.gasip.member.model.QMember(forProperty("member")) : null;
        this.professor = inits.isInitialized("professor") ? new com.example.gasip.professor.model.QProfessor(forProperty("professor"), inits.get("professor")) : null;
    }

}

