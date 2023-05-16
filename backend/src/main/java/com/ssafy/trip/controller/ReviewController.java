package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.Review;
import com.ssafy.trip.repository.board.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @GetMapping
    public ResponseEntity<?> selectAllReview(@PageableDefault(size = 5) Pageable pageable) {
       Page<Review> reviewList = reviewRepository.findAll(pageable);
        return new ResponseEntity<>(reviewList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registReview(@RequestBody Review review) throws SQLException {
       reviewRepository.save(review);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        reviewRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
