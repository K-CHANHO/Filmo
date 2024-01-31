package com.movie.test.report.view.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QViewEntity is a Querydsl query type for ViewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QViewEntity extends EntityPathBase<ViewEntity> {

    private static final long serialVersionUID = 2130141651L;

    public static final QViewEntity viewEntity = new QViewEntity("viewEntity");

    public final StringPath reportId = createString("reportId");

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QViewEntity(String variable) {
        super(ViewEntity.class, forVariable(variable));
    }

    public QViewEntity(Path<? extends ViewEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewEntity(PathMetadata metadata) {
        super(ViewEntity.class, metadata);
    }

}

