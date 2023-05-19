package com.ssafy.trip.dto.request;

import com.ssafy.trip.Entity.Board;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.OpenBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenBoardRequestDto {


    private Integer articleNo;
    private String subject;
    private String content;
    private Member member;
    private Integer hit;
    private String registerTime;

    public OpenBoard toEntity() {
        OpenBoard openBoard = OpenBoard.builder()
                .articleNo(articleNo)
                .subject(subject)
                .content(content)
                .member(member)
                .registerTime(registerTime)
                .hit(hit)
                .build();

        return openBoard;
    }

}
