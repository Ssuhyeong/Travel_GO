package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.dto.request.BoardRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.board.BoardRepository;
import com.ssafy.trip.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.sql.SQLException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private  final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/search")
    public ResponseEntity<?> selectByKeyword(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(value = "keyword", required = false) String keyword,
            Principal principal
    ){
        String userId = principal.getName();
        Page<Board> boards = boardService.findBySearchKeyword(pageable,keyword,userId);

        return new ResponseEntity<>(boards,HttpStatus.OK);

    }

    // 상세 정보 조회 ( no )
    @GetMapping("/{no}")
    public ResponseEntity<Board> selectBoardDetail(@PathVariable Integer no,Principal principal) throws SQLException {

        String userId = principal.getName();
        Board board = boardService.findByArticleNoAndMember_Email(no,userId);

        return new ResponseEntity<Board>(board,HttpStatus.OK);
    }

    // 게시판 등록
    @PostMapping
    public ResponseEntity<Object> registBoard(@RequestBody BoardRequestDto boardRequestDto, Principal principal) throws SQLException {
        String userId = principal.getName();

        boardService.saveArticle(boardRequestDto,userId);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Integer no,Principal principal) throws SQLException {

        Board board = boardRepository.findById(no).get();
        String userId = principal.getName();
        if(!board.getMember().getEmail().equals(userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        boardRepository.deleteById(no);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 수정
    @PutMapping("/update/{no}")
    public ResponseEntity<Object> ModifyBoard(
            @PathVariable Integer no,
            @RequestBody BoardRequestDto boardRequestDto,
            Principal principal
    ) throws SQLException {

        Board oldBoard = boardRepository.findById(no).get();
        String userId = principal.getName();
        if(!oldBoard.getMember().getEmail().equals(userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        boardRequestDto.setMember(memberRepository.findByEmail(userId).get());
        boardRequestDto.setArticleNo(no);
        Board board = boardRequestDto.toEntity();
        boardRepository.save(board);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
