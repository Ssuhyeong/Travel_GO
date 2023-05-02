package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<Board>> selectBoardList() throws SQLException {
        return new ResponseEntity<List<Board>>(boardRepository.findAll(), HttpStatus.OK);
    }

    // 상세 정보 조회 ( no )
    @GetMapping("/{no}")
    public ResponseEntity<Board> selectBoardDetail(@PathVariable String no) throws SQLException {

        return new ResponseEntity<Board>(boardRepository.getArticle(no), HttpStatus.OK);
    }

    // 게시판 등록
    @PostMapping
    public ResponseEntity<Object> registBoard(@RequestBody Board board) throws SQLException {
        boardRepository.registCar(board.getUserId(), board.getSubject(), board.getContent());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<Object> deleteBoard(@PathVariable String no) throws SQLException {
        log.debug("DELETE MAPPING");
        boardRepository.deleteArticle(no);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 수정
    @PostMapping("/update")
    public ResponseEntity<Object> ModifyBoard(@RequestBody Board board) throws SQLException {
        boardRepository.modifyArticle(board.getArticleNo(), board.getSubject(), board.getContent());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
