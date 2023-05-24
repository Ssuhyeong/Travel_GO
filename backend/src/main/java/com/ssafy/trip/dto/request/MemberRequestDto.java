package com.ssafy.trip.dto.request;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    private Integer id;
    private Role role;
    private String password;
    private String refreshToken;
    private String email;
    private String name;

    public Member toEntity() {
        Member member = Member.builder()
                .id(id)
                .password(password)
                .role(role)
                .refreshToken(refreshToken)
                .email(email)
                .name(name)
                .build();

        return member;
    }

    public MemberRequestDto(Integer id, String password, Role role, String refreshToken, String email, String name) {
        this.id = id;
        this.password = password;
        this.role = role;
        this.refreshToken = refreshToken;
        this.email = email;
        this.name = name;
    }



}
