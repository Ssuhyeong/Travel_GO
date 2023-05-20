package com.ssafy.trip.dto.response;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private Integer articleNo;
    private String email;
    private String content;
    private Integer star;
    private Integer attractionId;
    private String registerTime;

    // entity -> dto
    public ReviewResponseDto(Review review) {
        this.articleNo = review.getArticleNo();
        this.email = review.getMember().getEmail();
        this.content = review.getContent();
        this.star = review.getStar();
        this.attractionId = review.getAttraction().getContent_id();
        this.registerTime = review.getRegisterTime();

    }
}
