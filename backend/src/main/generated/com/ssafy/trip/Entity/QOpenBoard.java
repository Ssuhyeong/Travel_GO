package com.ssafy.trip.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOpenBoard is a Querydsl query type for OpenBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOpenBoard extends EntityPathBase<OpenBoard> {

    private static final long serialVersionUID = 1797560915L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOpenBoard openBoard = new QOpenBoard("openBoard");

    public final NumberPath<Integer> articleNo = createNumber("articleNo", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    public final QMember member;

    public final StringPath registerTime = createString("registerTime");

    public final StringPath subject = createString("subject");

    public QOpenBoard(String variable) {
        this(OpenBoard.class, forVariable(variable), INITS);
    }

    public QOpenBoard(Path<? extends OpenBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOpenBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOpenBoard(PathMetadata metadata, PathInits inits) {
        this(OpenBoard.class, metadata, inits);
    }

    public QOpenBoard(Class<? extends OpenBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

