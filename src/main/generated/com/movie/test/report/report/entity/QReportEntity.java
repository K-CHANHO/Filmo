package com.movie.test.report.report.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportEntity is a Querydsl query type for ReportEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportEntity extends EntityPathBase<ReportEntity> {

    private static final long serialVersionUID = 1814019377L;

    public static final QReportEntity reportEntity = new QReportEntity("reportEntity");

    public final com.movie.test.common.entity.QBaseTimeEntity _super = new com.movie.test.common.entity.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath movieId = createString("movieId");

    public final StringPath reportId = createString("reportId");

    public final StringPath title = createString("title");

    public final StringPath userId = createString("userId");

    public QReportEntity(String variable) {
        super(ReportEntity.class, forVariable(variable));
    }

    public QReportEntity(Path<? extends ReportEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportEntity(PathMetadata metadata) {
        super(ReportEntity.class, metadata);
    }

}

