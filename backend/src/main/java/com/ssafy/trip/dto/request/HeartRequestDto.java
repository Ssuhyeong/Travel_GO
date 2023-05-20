package com.ssafy.trip.dto.request;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Heart;
import com.ssafy.trip.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HeartRequestDto {

    private Integer id;
    private Attraction attraction;
    private Member member;

    public Heart toEntity() {
        Heart heart = Heart.builder()
                .id(id)
                .attraction(attraction)
                .member(member)
                .build();

        return heart;
    }
}
