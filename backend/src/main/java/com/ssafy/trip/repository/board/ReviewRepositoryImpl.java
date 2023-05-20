package com.ssafy.trip.repository.board;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trip.Entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.ssafy.trip.Entity.QReview.review;

public class ReviewRepositoryImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    public ReviewRepositoryImpl() {
        super(Review.class);
    }

    @Override
    public Page<Review> findBySearch(Pageable pageable, Integer attractionId) {
        JPQLQuery<Review> query =  jpaQueryFactory.selectFrom(review).where(eqAttraction(attractionId));

        List<Review> reviews = this.getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<Review>(reviews, pageable, query.fetchCount());
    }

    private BooleanExpression eqAttraction(Integer attractionId) {
        if(attractionId == null ) {
            return null;
        }
        return review.attraction.content_id.eq(attractionId);
    }
}
