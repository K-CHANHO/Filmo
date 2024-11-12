package com.movie.test.inquiry.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInquiryEntity is a Querydsl query type for InquiryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryEntity extends EntityPathBase<InquiryEntity> {

    private static final long serialVersionUID = -1470921577L;

    public static final QInquiryEntity inquiryEntity = new QInquiryEntity("inquiryEntity");

    public final com.movie.test.common.entity.QBaseTimeEntity _super = new com.movie.test.common.entity.QBaseTimeEntity(this);

    public final StringPath answerYN = createString("answerYN");

    public final StringPath category = createString("category");

    public final ArrayPath<byte[], Byte> content = createArray("content", byte[].class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.sql.Timestamp> deleteDate = _super.deleteDate;

    public final StringPath inquiryId = createString("inquiryId");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath title = createString("title");

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userId = createString("userId");

    public QInquiryEntity(String variable) {
        super(InquiryEntity.class, forVariable(variable));
    }

    public QInquiryEntity(Path<? extends InquiryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInquiryEntity(PathMetadata metadata) {
        super(InquiryEntity.class, metadata);
    }

}

