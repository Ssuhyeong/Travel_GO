package com.ssafy.trip.dto.request;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleRequestDto {

    private Integer id;
    private Member member;
    private Attraction attraction;
    private Integer day;

    public Schedule toEntity() {
        Schedule schedule = Schedule.builder()
                .id(id)
                .member(member)
                .attraction(attraction)
                .day(day)
                .build();

        return schedule;
    }
}
