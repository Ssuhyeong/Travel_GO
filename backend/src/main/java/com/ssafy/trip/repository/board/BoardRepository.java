package com.ssafy.trip.repository.board;

import com.ssafy.trip.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board,Integer> , BoardRepositoryCustom {
    // 게시판 내용 삽입
    @Transactional
    @Modifying
    @Query(value = "insert into board (user_id, subject, content) values (:user_id, :subject, :content)", nativeQuery = true)
    void registCar(@Param("user_id") String user_id,
                   @Param("subject") String subject,
                   @Param("content") String content);

    // 게시판 상세정보 조회
    @Query(value = "select article_no, user_id, subject, content, hit, register_time from board where article_no = :article_no", nativeQuery = true)
    Board getArticle(@Param("article_no") String article_no);

    // 게시판 내용 삭제
    @Transactional
    @Modifying
    @Query(value = "delete from board where article_no = :article_no", nativeQuery = true)
    void deleteArticle(@Param("article_no") String article_no);

    // 게시판 조회수 업데이트 관련
    @Transactional
    @Modifying
    @Query(value = "update board set hit = hit + 1 where article_no = :article_no", nativeQuery = true)
    void updateHit(@Param("article_no") String article_no);

    @Transactional
    @Modifying
    @Query(value = "update board set subject = :subject, content = :content where article_no = :article_no", nativeQuery = true)
    void modifyArticle(
            @Param("article_no") int article_no,
            @Param("subject") String subject,
            @Param("content") String content);
}
