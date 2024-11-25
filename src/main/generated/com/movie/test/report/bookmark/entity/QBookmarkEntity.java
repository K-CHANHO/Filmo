package com.movie.test.report.bookmark.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookmarkEntity is a Querydsl query type for BookmarkEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookmarkEntity extends EntityPathBase<BookmarkEntity> {

    private static final long serialVersionUID = 1078970741L;

    public static final QBookmarkEntity bookmarkEntity = new QBookmarkEntity("bookmarkEntity");

    public final com.movie.test.common.entity.QBaseTimeEntity _super = new com.movie.test.common.entity.QBaseTimeEntity(this);

    public final StringPath bookmarkId = createString("bookmarkId");

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.sql.Timestamp> deleteDate = _super.deleteDate;

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath reportId = createString("reportId");

    public final StringPath userId = createString("userId");

    public QBookmarkEntity(String variable) {
        super(BookmarkEntity.class, forVariable(variable));
    }

    public QBookmarkEntity(Path<? extends BookmarkEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookmarkEntity(PathMetadata metadata) {
        super(BookmarkEntity.class, metadata);
    }

}

