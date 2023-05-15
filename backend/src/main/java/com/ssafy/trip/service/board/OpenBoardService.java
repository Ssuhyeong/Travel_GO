package com.ssafy.trip.service.board;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.OpenBoard;
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
    public Page<OpenBoard> findBySearchKeyword(Pageable pageable, String keyword) {

        return boardRepository.findBySearch(pageable,keyword);
    }





}
