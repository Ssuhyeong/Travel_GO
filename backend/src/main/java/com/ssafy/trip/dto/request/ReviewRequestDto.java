package com.ssafy.trip.dto.request;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewRequestDto {

    private Integer articleNo;
    private Member member;
    private String content;
    private Integer star;
    private Attraction attraction;
    private String registerTime;


    //dto - > entity
    public Review toEntity() {
        Review review = Review.builder()
                .articleNo(articleNo)
                .member(member)
                .content(content)
                .star(star)
                .attraction(attraction)
                .registerTime(registerTime)
                .build();

        return review;
    }

}
