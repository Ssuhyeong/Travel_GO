package com.ssafy.trip.repository.board;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trip.Entity.OpenBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.ssafy.trip.Entity.QOpenBoard.openBoard;

public class OpenBoardRepositoryImpl extends QuerydslRepositorySupport implements OpenBoardRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    public OpenBoardRepositoryImpl() {
        super(OpenBoard.class);
    }

    @Override
    public Page<OpenBoard> findBySearch(Pageable pageable, String keyword) {
        JPQLQuery<OpenBoard> query =  jpaQueryFactory.selectFrom(openBoard).where(containKeyword(keyword));

        List<OpenBoard> openBoards = this.getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<OpenBoard>(openBoards, pageable, query.fetchCount());
    }

    private BooleanExpression containKeyword(String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return null;
        }
        return openBoard.title.containsIgnoreCase(keyword);
    }
}
