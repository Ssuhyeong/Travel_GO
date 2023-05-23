package com.ssafy.trip.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravelRoutes is a Querydsl query type for TravelRoutes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelRoutes extends EntityPathBase<TravelRoutes> {

    private static final long serialVersionUID = -182507507L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravelRoutes travelRoutes = new QTravelRoutes("travelRoutes");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public final NumberPath<Integer> scheduleInfo = createNumber("scheduleInfo", Integer.class);

    public final StringPath title = createString("title");

    public QTravelRoutes(String variable) {
        this(TravelRoutes.class, forVariable(variable), INITS);
    }

    public QTravelRoutes(Path<? extends TravelRoutes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravelRoutes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravelRoutes(PathMetadata metadata, PathInits inits) {
        this(TravelRoutes.class, metadata, inits);
    }

    public QTravelRoutes(Class<? extends TravelRoutes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

