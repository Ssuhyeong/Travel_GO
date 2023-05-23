package com.ssafy.trip.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchedule is a Querydsl query type for Schedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchedule extends EntityPathBase<Schedule> {

    private static final long serialVersionUID = 142336032L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchedule schedule = new QSchedule("schedule");

    public final QAttraction attraction;

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public final QTravelRoutes travelRoutes;

    public QSchedule(String variable) {
        this(Schedule.class, forVariable(variable), INITS);
    }

    public QSchedule(Path<? extends Schedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchedule(PathMetadata metadata, PathInits inits) {
        this(Schedule.class, metadata, inits);
    }

    public QSchedule(Class<? extends Schedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attraction = inits.isInitialized("attraction") ? new QAttraction(forProperty("attraction"), inits.get("attraction")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.travelRoutes = inits.isInitialized("travelRoutes") ? new QTravelRoutes(forProperty("travelRoutes"), inits.get("travelRoutes")) : null;
    }

}

