package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Review;
import com.ssafy.trip.repository.attracion.AttractionRepository;
import com.ssafy.trip.repository.board.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AttractionRepository attractionRepository;

    public void save(Integer attractionId, Review review){

        Attraction attraction = attractionRepository.findById(attractionId).get();
        log.debug("ATTRACTION 출력 : " , attraction , "AID : ",attractionId );
        review.setReview(attraction);

        reviewRepository.save(review);

    }

}
