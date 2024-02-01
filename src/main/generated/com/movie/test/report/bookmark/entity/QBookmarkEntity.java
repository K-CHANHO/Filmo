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

    public final NumberPath<Long> bookmarkId = createNumber("bookmarkId", Long.class);

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

