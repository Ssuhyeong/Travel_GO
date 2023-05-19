package com.ssafy.trip.repository.board;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trip.Entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.ssafy.trip.Entity.QBoard.board;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    public BoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> findBySearch(Pageable pageable, String keyword,String userId) {
        JPQLQuery<Board> query =  jpaQueryFactory.selectFrom(board).where(containKeyword(keyword),containUserId(userId));

        List<Board> boards = this.getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<Board>(boards, pageable, query.fetchCount());
    }

    private BooleanExpression containKeyword(String keyword) {
        if(keyword == null || keyword.isEmpty()) {
            return null;
        }
        return board.subject.containsIgnoreCase(keyword);
    }

    private BooleanExpression containUserId(String userId) {
        if(userId == null || userId.isEmpty()) {
            return null;
        }
        return board.member.email.containsIgnoreCase(userId);
    }
}
