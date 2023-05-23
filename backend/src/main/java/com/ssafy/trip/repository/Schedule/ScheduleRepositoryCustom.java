package com.ssafy.trip.repository.Schedule;

import com.ssafy.trip.Entity.Attraction;

import java.util.List;

public interface ScheduleRepositoryCustom {

    List<Attraction> findByDayAndScheduleNum(String userId , Integer day , Integer scheduleNum);
}
