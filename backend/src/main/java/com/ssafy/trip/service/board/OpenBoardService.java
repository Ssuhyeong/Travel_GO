package com.ssafy.trip.service.board;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.Faq;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.OpenBoard;
import com.ssafy.trip.dto.request.BoardRequestDto;
import com.ssafy.trip.dto.request.OpenBoardRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.board.BoardRepository;
import com.ssafy.trip.repository.board.OpenBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenBoardService {

    private final OpenBoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Page<OpenBoard> findBySearchKeyword(Pageable pageable, String keyword) {

        return boardRepository.findBySearch(pageable,keyword);
    }

    public void update(Integer articleNo , OpenBoard openBoard){
       OpenBoard oldboard = boardRepository.findById(articleNo).orElseThrow(() -> new IllegalArgumentException("해당하는 게시물이 없습니다."));
        boardRepository.save(openBoard);
    }

    public OpenBoard findByArticleNoAndMember_Email(Integer no, String userId) {
        OpenBoard board = boardRepository.findById(no).get();
        Member member = memberRepository.findByEmail(userId).get();
        return board;
    }

    public void saveArticle(OpenBoardRequestDto openBoardRequestDto, String userId) {
        Member member = memberRepository.findByEmail(userId).get();
        openBoardRequestDto.setMember(member);

        OpenBoard openBoard = openBoardRequestDto.toEntity();
        boardRepository.save(openBoard);
    }




}
