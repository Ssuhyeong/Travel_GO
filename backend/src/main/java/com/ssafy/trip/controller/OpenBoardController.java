package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.OpenBoard;
import com.ssafy.trip.repository.board.OpenBoardRepository;
import com.ssafy.trip.service.board.OpenBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/open-board")
public class OpenBoardController {

    private final OpenBoardService boardService;
    private final OpenBoardRepository boardRepository;

    @GetMapping("/search")
    public ResponseEntity<?> selectByKeyword(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(value = "keyword", required = false) String keyword
    ){

        Page<OpenBoard> boards = boardService.findBySearchKeyword(pageable,keyword);

        return new ResponseEntity<>(boards, HttpStatus.OK);

    }

    // 상세 정보 조회 ( no )
    @GetMapping("/{no}")
    public ResponseEntity<Board> selectBoardDetail(@PathVariable int no) throws SQLException {

        return new ResponseEntity<Board>(boardRepository.getArticle(no), HttpStatus.OK);
    }

    // 게시판 등록
    @PostMapping
    public ResponseEntity<Object> registBoard(@RequestBody OpenBoard board) throws SQLException {
        boardRepository.registCar(board.getUserId(), board.getTitle(), board.getContent());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<Object> deleteBoard(@PathVariable int no) throws SQLException {
        log.debug("DELETE MAPPING");
        boardRepository.deleteArticle(no);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 수정
    @PostMapping("/update")
    public ResponseEntity<Object> ModifyBoard(@RequestBody OpenBoard board) throws SQLException {
        boardRepository.modifyArticle(board.getId(), board.getTitle(), board.getContent());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
