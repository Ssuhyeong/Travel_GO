package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.OpenBoard;
import com.ssafy.trip.dto.request.OpenBoardRequestDto;
import com.ssafy.trip.repository.MemberRepository;
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
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.sql.SQLException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/open-board")
public class OpenBoardController {

    private final OpenBoardService openBoardService;
    private final OpenBoardRepository openBoardRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/search")
    public ResponseEntity<?> selectByKeyword(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(value = "keyword", required = false) String keyword,
            Principal principal
    ){

        String userId = principal.getName();
        Member member = memberRepository.findByEmail(userId).get();

        //Page<OpenBoard> boards = openBoardService.findBySearchKeyword(pageable,keyword,userId);
        Page<OpenBoard> boards = openBoardService.findBySearchKeyword(pageable,keyword);

        return new ResponseEntity<>(boards, HttpStatus.OK);

    }

    // 상세 정보 조회 ( no )
    @GetMapping("/{no}")
    public ResponseEntity<OpenBoard> selectBoardDetail(@PathVariable Integer no,Principal principal) throws SQLException {

        String userId = principal.getName();
        OpenBoard board = openBoardService.findByArticleNoAndMember_Email(no,userId);

        return new ResponseEntity<OpenBoard>(board,HttpStatus.OK);
    }

    // 게시판 등록
    @PostMapping
    public ResponseEntity<Object> registBoard(@RequestBody OpenBoardRequestDto openBoardRequestDto, Principal principal) throws SQLException {
        String userId = principal.getName();

        openBoardService.saveArticle(openBoardRequestDto,userId);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Integer no, Principal principal) throws SQLException {
        OpenBoard openBoard = openBoardRepository.findById(no).get();
        String userId = principal.getName();
        if(!openBoard.getMember().getEmail().equals(userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        openBoardRepository.deleteById(no);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PutMapping ("/update/{no}")
    public ResponseEntity<Object> ModifyBoard(
            @PathVariable Integer no,
            @RequestBody OpenBoardRequestDto openBoardRequestDto,
            Principal principal
    ) throws SQLException {

        OpenBoard oldBoard = openBoardRepository.findById(no).get();
        String userId = principal.getName();
        if(!oldBoard.getMember().getEmail().equals(userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        openBoardRequestDto.setMember(memberRepository.findByEmail(userId).get());
        openBoardRequestDto.setArticleNo(no);
        OpenBoard openBoard = openBoardRequestDto.toEntity();
        openBoardRepository.save(openBoard);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
