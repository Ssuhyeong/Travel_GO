package com.ssafy.trip.dto.request;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardRequestDto {

    private Integer articleNo;
    private String subject;
    private String content;
    private Member member;
    private Integer hit;
    private String registerTime;

    public Board toEntity() {
        Board board = Board.builder()
                .articleNo(articleNo)
                .subject(subject)
                .content(content)
                .member(member)
                .registerTime(registerTime)
                .hit(hit)
                .build();

        return board;
    }

}
