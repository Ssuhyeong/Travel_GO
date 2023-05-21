package com.ssafy.trip.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttraction is a Querydsl query type for Attraction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttraction extends EntityPathBase<Attraction> {

    private static final long serialVersionUID = 210529968L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttraction attraction = new QAttraction("attraction");

    public final StringPath addr1 = createString("addr1");

    public final NumberPath<Integer> content_id = createNumber("content_id", Integer.class);

    public final NumberPath<Integer> content_type_id = createNumber("content_type_id", Integer.class);

    public final StringPath first_image = createString("first_image");

    public final StringPath latitude = createString("latitude");

    public final NumberPath<Integer> like_count = createNumber("like_count", Integer.class);

    public final StringPath longitude = createString("longitude");

    public final QMember member;

    public final StringPath overview = createString("overview");

    public final NumberPath<Integer> sido_code = createNumber("sido_code", Integer.class);

    public final StringPath title = createString("title");

    public QAttraction(String variable) {
        this(Attraction.class, forVariable(variable), INITS);
    }

    public QAttraction(Path<? extends Attraction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttraction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttraction(PathMetadata metadata, PathInits inits) {
        this(Attraction.class, metadata, inits);
    }

    public QAttraction(Class<? extends Attraction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

