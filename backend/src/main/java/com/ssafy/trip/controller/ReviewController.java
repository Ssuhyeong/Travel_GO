package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Review;
import com.ssafy.trip.repository.board.ReviewRepository;
import com.ssafy.trip.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> selectAllReview(@PageableDefault(size = 5) Pageable pageable) {
       Page<Review> reviewList = reviewRepository.findAll(pageable);
        return new ResponseEntity<>(reviewList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registReview(
            @RequestParam Integer attractionId,
            @RequestBody Review review
    ) throws SQLException {
        reviewService.save(attractionId,review);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // TODO: LOGIN 구현 후 userID 연결 예정
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        reviewRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
