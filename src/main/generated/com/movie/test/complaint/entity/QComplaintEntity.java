package com.movie.test.complaint.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComplaintEntity is a Querydsl query type for ComplaintEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComplaintEntity extends EntityPathBase<ComplaintEntity> {

    private static final long serialVersionUID = -789544553L;

    public static final QComplaintEntity complaintEntity = new QComplaintEntity("complaintEntity");

    public final com.movie.test.common.entity.QBaseTimeEntity _super = new com.movie.test.common.entity.QBaseTimeEntity(this);

    public final StringPath complaintId = createString("complaintId");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath reportId = createString("reportId");

    public final StringPath userId = createString("userId");

    public QComplaintEntity(String variable) {
        super(ComplaintEntity.class, forVariable(variable));
    }

    public QComplaintEntity(Path<? extends ComplaintEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComplaintEntity(PathMetadata metadata) {
        super(ComplaintEntity.class, metadata);
    }

}

