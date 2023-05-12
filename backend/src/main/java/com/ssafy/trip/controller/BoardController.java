package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.repository.board.BoardRepository;
import com.ssafy.trip.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

   private final BoardService boardService;
    private  final BoardRepository boardRepository;

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<Board>> selectBoardList() throws SQLException {
        return new ResponseEntity<List<Board>>(boardRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> selectByKeyword(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(value = "keyword", required = false) String keyword
    ){

        Page<Board> boards = boardService.findBySearchKeyword(pageable,keyword);

        return new ResponseEntity<>(boards,HttpStatus.OK);

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
