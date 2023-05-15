package com.ssafy.trip.repository.board;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.OpenBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OpenBoardRepositoryCustom {

    Page<OpenBoard> findBySearch(Pageable pageable, String keyword);

}
