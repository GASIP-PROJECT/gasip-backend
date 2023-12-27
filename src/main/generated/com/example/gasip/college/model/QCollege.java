package com.example.gasip.college.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCollege is a Querydsl query type for College
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCollege extends EntityPathBase<College> {

    private static final long serialVersionUID = 413371772L;

    public static final QCollege college = new QCollege("college");

    public final NumberPath<Long> collegeId = createNumber("collegeId", Long.class);

    public final StringPath collegeName = createString("collegeName");

    public QCollege(String variable) {
        super(College.class, forVariable(variable));
    }

    public QCollege(Path<? extends College> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCollege(PathMetadata metadata) {
        super(College.class, metadata);
    }

}

