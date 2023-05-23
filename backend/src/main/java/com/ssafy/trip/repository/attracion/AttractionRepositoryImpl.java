package com.ssafy.trip.repository.attracion;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trip.Entity.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.ssafy.trip.Entity.QAttraction.attraction;

public class AttractionRepositoryImpl extends QuerydslRepositorySupport implements AttractionRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    public AttractionRepositoryImpl() {
        super(Attraction.class);

    }

    @Override
    public Page<Attraction> findBySearchOrderBy(Pageable pageable,String keyword, Integer sidoCode,Integer contentTypeId){
        JPQLQuery<Attraction> query =  queryFactory.selectFrom(attraction)
                .where(containKeyword(keyword), eqSido(sidoCode),eqContent(contentTypeId))
                .orderBy(attraction.like_count.desc());

        List<Attraction> attractions = this.getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<Attraction>(attractions, pageable, query.fetchCount());
    }


    @Override
    public Page<Attraction> findBySearchOption(
            Pageable pageable, Integer sidoCode, Integer contentTypeId, String keyword, Integer contentId
    ) {
        JPQLQuery<Attraction> query =  queryFactory.selectFrom(attraction)
                .where(eqSido(sidoCode), eqContent(contentTypeId), containKeyword(keyword), eqContentId(contentId));

        List<Attraction> attractions = this.getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<Attraction>(attractions, pageable, query.fetchCount());
    }


    private BooleanExpression eqSido(Integer sidoCode) {
        if(sidoCode == null ) {
            return null;
        }
        return attraction.sido_code.eq(sidoCode);
    }

    private BooleanExpression containKeyword(String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return null;
        }
        return attraction.title.containsIgnoreCase(keyword);
    }

    private BooleanExpression eqContent(Integer contentTypeId) {
        if(contentTypeId == null ) {
            return null;
        }
        return attraction.content_type_id.eq(contentTypeId);
    }

    private BooleanExpression eqContentId(Integer contentId) {
        if(contentId == null ) {
            return null;
        }
        return attraction.content_id.eq(contentId);
    }


    //좋아요 관련

    @Override
    public void updateLikeCount(Attraction attraction1) {

            queryFactory.update(attraction)
                    .set(attraction.like_count, attraction.like_count.add(1))
                    .where(attraction.eq(attraction1))
                    .execute();

    }

    @Override
    public void updateDownLike(Attraction attraction1){
        queryFactory.update(attraction)
                .set(attraction.like_count , attraction.like_count.subtract(1))
                .where(attraction.eq(attraction1))
                .execute();
    }

    @Override
    public List<Attraction> findByBestLike() {

        List<Attraction> bestLike =
        queryFactory.selectFrom(attraction).orderBy(attraction.like_count.desc()).limit(5).fetch();

        return bestLike;
    }
}
