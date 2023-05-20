package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Review;
import com.ssafy.trip.dto.request.ReviewRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.attracion.AttractionRepository;
import com.ssafy.trip.repository.board.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AttractionRepository attractionRepository;
    private final MemberRepository memberRepository;

    public void save(Integer attractionId, ReviewRequestDto reviewRequestDto, String userId){

        Attraction attraction = attractionRepository.findById(attractionId).get();
        Member member = memberRepository.findByEmail(userId).get();

        reviewRequestDto.setMember(member);
        reviewRequestDto.setAttraction(attraction);
        Review review = reviewRequestDto.toEntity();

        reviewRepository.save(review);

    }

    public List<Review> getReview(Integer attractionId){

        Attraction attraction = attractionRepository.findById(attractionId).get();

        return reviewRepository.findByAttraction(attraction);
    }

}
