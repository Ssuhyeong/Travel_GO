package com.ssafy.trip.dto.response;

import com.ssafy.trip.Entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Integer articleNo;
    private String subject;
    private String content;
    private String name;
    private Integer hit;
    private String registerTime;

    // entity -> dto
    public BoardResponseDto(Board board) {
        this.articleNo = board.getArticleNo();
        this.subject = board.getSubject();
        this.content = board.getContent();
        this.name = board.getMember().getName();
        this.hit = board.getHit();
        this.registerTime = board.getRegisterTime();
    }

}
