package com.movie.test.user.userinfo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -491429866L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final com.movie.test.common.entity.QBaseTimeEntity _super = new com.movie.test.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.sql.Timestamp> deleteDate = _super.deleteDate;

    public final StringPath email = createString("email");

    public final StringPath introduction = createString("introduction");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final DateTimePath<java.sql.Timestamp> lastLoginDate = createDateTime("lastLoginDate", java.sql.Timestamp.class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath nickname = createString("nickname");

    public final StringPath profileUrl = createString("profileUrl");

    public final StringPath roles = createString("roles");

    public final StringPath type = createString("type");

    public final StringPath userId = createString("userId");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

