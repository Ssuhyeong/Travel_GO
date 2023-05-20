package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Review;
import com.ssafy.trip.dto.request.ReviewRequestDto;
import com.ssafy.trip.repository.board.ReviewRepository;
import com.ssafy.trip.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    // 조회 -> attractionId
    @GetMapping
    public ResponseEntity<?> selectAllReview(
            @RequestParam(required = false) Integer attractionId,
            Principal principal) {
        List<Review> reviewList = reviewService.getReview(attractionId);
        return new ResponseEntity<>(reviewList,HttpStatus.OK);
    }

    // review 등록
    @PostMapping
    public ResponseEntity<Object> registReview(
            @RequestParam Integer attractionId,
            @RequestBody ReviewRequestDto reviewRequestDto,
            Principal principle
    ) throws SQLException {
        String userId = principle.getName();
        reviewService.save(attractionId,reviewRequestDto,userId);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
