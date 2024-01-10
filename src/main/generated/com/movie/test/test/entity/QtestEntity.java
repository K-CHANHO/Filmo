package com.movie.test.test.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QtestEntity is a Querydsl query type for testEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QtestEntity extends EntityPathBase<testEntity> {

    private static final long serialVersionUID = -546648683L;

    public static final QtestEntity testEntity = new QtestEntity("testEntity");

    public final StringPath param1 = createString("param1");

    public final StringPath param2 = createString("param2");

    public QtestEntity(String variable) {
        super(testEntity.class, forVariable(variable));
    }

    public QtestEntity(Path<? extends testEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QtestEntity(PathMetadata metadata) {
        super(testEntity.class, metadata);
    }

}

