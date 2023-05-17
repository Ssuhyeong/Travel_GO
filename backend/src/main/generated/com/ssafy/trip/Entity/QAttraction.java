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

    public static final QAttraction attraction = new QAttraction("attraction");

    public final StringPath addr1 = createString("addr1");

    public final NumberPath<Integer> content_id = createNumber("content_id", Integer.class);

    public final NumberPath<Integer> content_type_id = createNumber("content_type_id", Integer.class);

    public final StringPath first_image = createString("first_image");

    public final StringPath latitude = createString("latitude");

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final StringPath longitude = createString("longitude");

    public final StringPath overview = createString("overview");

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final NumberPath<Integer> sido_code = createNumber("sido_code", Integer.class);

    public final StringPath title = createString("title");

    public QAttraction(String variable) {
        super(Attraction.class, forVariable(variable));
    }

    public QAttraction(Path<? extends Attraction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttraction(PathMetadata metadata) {
        super(Attraction.class, metadata);
    }

}

