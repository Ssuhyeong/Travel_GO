package com.ssafy.trip.repository.Schedule;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Schedule;

import java.util.List;

public interface ScheduleRepositoryCustom {

    List<Attraction> findByDay(String userId , Integer day);
}
