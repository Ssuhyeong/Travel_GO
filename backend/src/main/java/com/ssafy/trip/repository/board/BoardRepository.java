package com.ssafy.trip.repository.board;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.dto.response.BoardResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Integer> , BoardRepositoryCustom {

    // 게시판 조회수 업데이트 관련
    @Transactional
    @Modifying
    @Query(value = "update board set hit = hit + 1 where article_no = :article_no", nativeQuery = true)
    void updateHit(@Param("article_no") String article_no);

}
