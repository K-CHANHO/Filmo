package com.movie.test.user.follow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFollowEntity is a Querydsl query type for FollowEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFollowEntity extends EntityPathBase<FollowEntity> {

    private static final long serialVersionUID = 1608280756L;

    public static final QFollowEntity followEntity = new QFollowEntity("followEntity");

    public final com.movie.test.common.entity.QBaseTimeEntity _super = new com.movie.test.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.sql.Timestamp> deleteDate = _super.deleteDate;

    public final StringPath followId = createString("followId");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath targetId = createString("targetId");

    public final StringPath userId = createString("userId");

    public QFollowEntity(String variable) {
        super(FollowEntity.class, forVariable(variable));
    }

    public QFollowEntity(Path<? extends FollowEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFollowEntity(PathMetadata metadata) {
        super(FollowEntity.class, metadata);
    }

}

