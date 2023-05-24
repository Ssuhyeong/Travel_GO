package com.ssafy.trip.repository.Schedule;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.Entity.TravelRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.ssafy.trip.Entity.QSchedule.schedule;
import static com.ssafy.trip.Entity.QTravelRoutes.travelRoutes;

public class TravelRoutesRepositoryImpl extends QuerydslRepositorySupport implements TravelRoutesRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    public TravelRoutesRepositoryImpl() {
        super(TravelRoutes.class);
    }

    @Override
    public List<Schedule> findByEamilAndScheduleInfo(String userId, Integer scheduleInfo) {

        List<Schedule> scheduleList =
        queryFactory.selectFrom(schedule)
                .where(schedule.member.email.eq(userId).and(schedule.scheduleNum.eq(scheduleInfo))).fetch();

        return scheduleList;
    }

    @Override
    public TravelRoutes findByEamilAndScheduleNum(String userId, Integer scheduleInfo) {

       TravelRoutes travel =
                queryFactory.selectFrom(travelRoutes)
                        .where(travelRoutes.member.email.eq(userId).and(travelRoutes.scheduleInfo.eq(scheduleInfo))).fetchFirst();

        return travel;
    }

    @Override
    public List<TravelRoutes> findBySameUser(String userId) {
        List<TravelRoutes> travelRoutesList =
        queryFactory.selectFrom(travelRoutes).where(travelRoutes.member.email.eq(userId)).groupBy(travelRoutes.member.email).fetch();
        return travelRoutesList;
    }
}
