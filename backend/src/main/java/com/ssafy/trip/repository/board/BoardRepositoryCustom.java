package com.ssafy.trip.repository.board;

import com.ssafy.trip.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> findBySearch(Pageable pageable, String keyword, String userId);
}
