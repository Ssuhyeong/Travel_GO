package com.ssafy.trip.repository.board;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.Faq;
import com.ssafy.trip.Entity.OpenBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OpenBoardRepository extends JpaRepository<OpenBoard, Integer> , OpenBoardRepositoryCustom{

    // 게시판 조회수 업데이트 관련
    @Transactional
    @Modifying
    @Query(value = "update open_board set hit = hit + 1 where article_no = :article_no", nativeQuery = true)
    void updateHit(@Param("article_no") String article_no);

    @Override
    Optional<OpenBoard> findById(Integer integer);


}
