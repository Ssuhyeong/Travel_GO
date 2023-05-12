package com.ssafy.trip.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1579464189L;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Integer> articleNo = createNumber("articleNo", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    public final DateTimePath<java.sql.Timestamp> registerTime = createDateTime("registerTime", java.sql.Timestamp.class);

    public final StringPath subject = createString("subject");

    public final StringPath userId = createString("userId");

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

