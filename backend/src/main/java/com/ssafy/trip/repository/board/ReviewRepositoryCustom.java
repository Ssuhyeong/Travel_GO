package com.ssafy.trip.repository.board;

import com.ssafy.trip.Entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    Page<Review> findBySearch(Pageable pageable, Integer attractionId);

}
