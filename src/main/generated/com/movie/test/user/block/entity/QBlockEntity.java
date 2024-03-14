package com.movie.test.user.block.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlockEntity is a Querydsl query type for BlockEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockEntity extends EntityPathBase<BlockEntity> {

    private static final long serialVersionUID = 285484504L;

    public static final QBlockEntity blockEntity = new QBlockEntity("blockEntity");

    public final com.movie.test.common.entity.QBaseTimeEntity _super = new com.movie.test.common.entity.QBaseTimeEntity(this);

    public final StringPath blockId = createString("blockId");

    //inherited
    public final DateTimePath<java.sql.Timestamp> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath targetId = createString("targetId");

    public final StringPath userId = createString("userId");

    public QBlockEntity(String variable) {
        super(BlockEntity.class, forVariable(variable));
    }

    public QBlockEntity(Path<? extends BlockEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlockEntity(PathMetadata metadata) {
        super(BlockEntity.class, metadata);
    }

}

