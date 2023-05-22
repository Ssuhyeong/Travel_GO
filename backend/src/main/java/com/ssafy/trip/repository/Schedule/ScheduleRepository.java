package com.ssafy.trip.repository.Schedule;

import com.ssafy.trip.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> , ScheduleRepositoryCustom {
}
