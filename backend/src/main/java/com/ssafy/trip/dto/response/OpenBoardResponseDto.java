package com.ssafy.trip.dto.response;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.OpenBoard;
import lombok.Getter;

@Getter
public class OpenBoardResponseDto {

    private Integer articleNo;
    private String subject;
    private String content;
    private String email;
    private Integer hit;
    private String registerTime;

    // entity -> dto
    public OpenBoardResponseDto(OpenBoard board) {
        this.articleNo = board.getArticleNo();
        this.subject = board.getSubject();
        this.content = board.getContent();
        this.email = board.getMember().getEmail();
        this.hit = board.getHit();
        this.registerTime = board.getRegisterTime();
    }
}
