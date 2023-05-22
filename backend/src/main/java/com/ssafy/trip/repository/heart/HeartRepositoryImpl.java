package com.ssafy.trip.repository.heart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trip.Entity.Heart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.ssafy.trip.Entity.QHeart.heart;

public class HeartRepositoryImpl extends QuerydslRepositorySupport implements HeartRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    public HeartRepositoryImpl() {
        super(Heart.class);
    }


    @Override
    public List<Integer> findBySearch(String userId) {

        List<Integer> attractionIdResult = queryFactory.select(heart.attraction.content_id).from(heart).where(heart.member.email.eq(userId)).fetch();

        return attractionIdResult;
    }
}
