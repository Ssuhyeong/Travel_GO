package com.ssafy.trip.repository.Schedule;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.ssafy.trip.Entity.QSchedule.schedule;

public class ScheduleRepositoryImpl extends QuerydslRepositorySupport implements ScheduleRepositoryCustom{

    @Autowired
    private JPAQueryFactory queryFactory;

    public ScheduleRepositoryImpl() {
        super(Schedule.class);
    }

    @Override
    public List<Attraction> findByDayAndScheduleNum(String userId,  Integer day , Integer scheduleNum) {

        List<Attraction> travelInfoDay = queryFactory
                .select(schedule.attraction)
                .from(schedule)
                .where(schedule.member.email.eq(userId).and(schedule.day.eq(day))).fetch();//.and(schedule.scheduleNum.eq(scheduleNum))).fetch();

        return travelInfoDay;
    }

    @Override
    public List<Schedule> findByEmailAndScheduleNum(String userId, Integer scheduleNum) {

        List<Schedule> scheduleList = queryFactory.selectFrom(schedule)
                .where(schedule.member.email.eq(userId).and(schedule.scheduleNum.eq(scheduleNum))).fetch();

        return scheduleList;
    }
}
