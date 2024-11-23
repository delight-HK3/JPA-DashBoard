package com.spring.jpatest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QhibernateMainEntity is a Querydsl query type for hibernateMainEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QhibernateMainEntity extends EntityPathBase<hibernateMainEntity> {

    private static final long serialVersionUID = 990117208L;

    public static final QhibernateMainEntity hibernateMainEntity = new QhibernateMainEntity("hibernateMainEntity");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final NumberPath<Integer> uniqueNum = createNumber("uniqueNum", Integer.class);

    public QhibernateMainEntity(String variable) {
        super(hibernateMainEntity.class, forVariable(variable));
    }

    public QhibernateMainEntity(Path<? extends hibernateMainEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QhibernateMainEntity(PathMetadata metadata) {
        super(hibernateMainEntity.class, metadata);
    }

}

