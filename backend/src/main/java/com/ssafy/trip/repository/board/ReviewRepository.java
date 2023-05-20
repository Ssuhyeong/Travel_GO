package com.ssafy.trip.repository.board;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer>{

    List<Review> findByAttraction(Attraction attraction);
}
