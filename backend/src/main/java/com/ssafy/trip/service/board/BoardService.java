package com.ssafy.trip.service.board;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    public Page<Board> findBySearchKeyword(Pageable pageable,  String keyword) {

        return boardRepository.findBySearch(pageable,keyword);
    }

}
