package com.ssafy.trip.service.board;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.dto.request.BoardRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    public Page<Board> findBySearchKeyword(Pageable pageable,  String keyword) {

        return boardRepository.findBySearch(pageable,keyword);
    }

    public Board findByArticleNoAndMember_Email(Integer no, String userId) {
        Board board = boardRepository.findById(no).get();
        Member member = memberRepository.findByEmail(userId).get();
        return board;
    }

    public void saveArticle(BoardRequestDto boardRequestDto, String userId) {
        Member member = memberRepository.findByEmail(userId).get();
        boardRequestDto.setMember(member);

        Board board = boardRequestDto.toEntity();
        boardRepository.save(board);
    }



}
