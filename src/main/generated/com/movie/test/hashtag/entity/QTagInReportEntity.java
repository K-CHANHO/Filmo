package com.movie.test.hashtag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTagInReportEntity is a Querydsl query type for TagInReportEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTagInReportEntity extends EntityPathBase<TagInReportEntity> {

    private static final long serialVersionUID = -844156578L;

    public static final QTagInReportEntity tagInReportEntity = new QTagInReportEntity("tagInReportEntity");

    public final NumberPath<Long> noMeaningId = createNumber("noMeaningId", Long.class);

    public final StringPath reportId = createString("reportId");

    public final NumberPath<Long> tagId = createNumber("tagId", Long.class);

    public QTagInReportEntity(String variable) {
        super(TagInReportEntity.class, forVariable(variable));
    }

    public QTagInReportEntity(Path<? extends TagInReportEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTagInReportEntity(PathMetadata metadata) {
        super(TagInReportEntity.class, metadata);
    }

}

