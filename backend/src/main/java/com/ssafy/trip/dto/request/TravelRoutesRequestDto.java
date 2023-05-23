package com.ssafy.trip.dto.request;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Schedule;
import com.ssafy.trip.Entity.TravelRoutes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TravelRoutesRequestDto {

    private Integer id;
    private String title;
    private Member member;
    private Integer scheduleInfo;

    public TravelRoutes toEntity() {
        TravelRoutes travelRoutes = TravelRoutes.builder()
                .id(id)
                .title(title)
                .member(member)
                .scheduleInfo(scheduleInfo)
                .build();

        return travelRoutes;
    }
}
