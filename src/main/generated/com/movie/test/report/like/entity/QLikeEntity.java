package com.movie.test.report.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLikeEntity is a Querydsl query type for LikeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeEntity extends EntityPathBase<LikeEntity> {

    private static final long serialVersionUID = 1640412599L;

    public static final QLikeEntity likeEntity = new QLikeEntity("likeEntity");

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final StringPath likeId = createString("likeId");

    public final StringPath targetId = createString("targetId");

    public final StringPath type = createString("type");

    public final StringPath userId = createString("userId");

    public QLikeEntity(String variable) {
        super(LikeEntity.class, forVariable(variable));
    }

    public QLikeEntity(Path<? extends LikeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLikeEntity(PathMetadata metadata) {
        super(LikeEntity.class, metadata);
    }

}

