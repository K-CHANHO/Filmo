package com.movie.test.notification.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotificationEntity is a Querydsl query type for NotificationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotificationEntity extends EntityPathBase<NotificationEntity> {

    private static final long serialVersionUID = 95321447L;

    public static final QNotificationEntity notificationEntity = new QNotificationEntity("notificationEntity");

    public final com.movie.test.common.entity.QBaseTimeEntity _super = new com.movie.test.common.entity.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> notificationId = createNumber("notificationId", Long.class);

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public QNotificationEntity(String variable) {
        super(NotificationEntity.class, forVariable(variable));
    }

    public QNotificationEntity(Path<? extends NotificationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotificationEntity(PathMetadata metadata) {
        super(NotificationEntity.class, metadata);
    }

}

