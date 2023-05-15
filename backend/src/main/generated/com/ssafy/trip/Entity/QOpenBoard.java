package com.ssafy.trip.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOpenBoard is a Querydsl query type for OpenBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOpenBoard extends EntityPathBase<OpenBoard> {

    private static final long serialVersionUID = 1797560915L;

    public static final QOpenBoard openBoard = new QOpenBoard("openBoard");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.sql.Timestamp> registerTime = createDateTime("registerTime", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final StringPath userId = createString("userId");

    public QOpenBoard(String variable) {
        super(OpenBoard.class, forVariable(variable));
    }

    public QOpenBoard(Path<? extends OpenBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOpenBoard(PathMetadata metadata) {
        super(OpenBoard.class, metadata);
    }

}

