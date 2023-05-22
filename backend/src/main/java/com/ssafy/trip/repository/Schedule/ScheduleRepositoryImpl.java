package com.ssafy.trip.repository.Schedule;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.ssafy.trip.Entity.QHeart.heart;
import static com.ssafy.trip.Entity.QSchedule.schedule;

public class ScheduleRepositoryImpl extends QuerydslRepositorySupport implements ScheduleRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    public ScheduleRepositoryImpl() {
        super(Schedule.class);
    }

    @Override
    public List<Attraction> findByDay(String userId,  Integer day) {

        List<Attraction> travelInfoDay = queryFactory
                .select(schedule.attraction).from(schedule).where(schedule.member.email.eq(userId).and(schedule.day.eq(day))).fetch();

        return travelInfoDay;
    }
}
