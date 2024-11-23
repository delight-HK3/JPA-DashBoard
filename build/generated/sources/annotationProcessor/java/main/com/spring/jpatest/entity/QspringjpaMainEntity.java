package com.spring.jpatest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QspringjpaMainEntity is a Querydsl query type for springjpaMainEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QspringjpaMainEntity extends EntityPathBase<springjpaMainEntity> {

    private static final long serialVersionUID = 1576821940L;

    public static final QspringjpaMainEntity springjpaMainEntity = new QspringjpaMainEntity("springjpaMainEntity");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final NumberPath<Integer> uniqueNum = createNumber("uniqueNum", Integer.class);

    public QspringjpaMainEntity(String variable) {
        super(springjpaMainEntity.class, forVariable(variable));
    }

    public QspringjpaMainEntity(Path<? extends springjpaMainEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QspringjpaMainEntity(PathMetadata metadata) {
        super(springjpaMainEntity.class, metadata);
    }

}

