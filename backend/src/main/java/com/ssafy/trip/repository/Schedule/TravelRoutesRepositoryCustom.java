package com.ssafy.trip.repository.Schedule;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.Entity.TravelRoutes;

import java.util.List;

public interface TravelRoutesRepositoryCustom {

    List<Schedule> findByEamilAndScheduleInfo(String userId, Integer scheduleInfo);
    TravelRoutes findByEamilAndScheduleNum(String userId, Integer scheduleNum);
    List<TravelRoutes> findBySameUser(String userId);

}
