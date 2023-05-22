package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Role;
import com.ssafy.trip.dto.UserSignDto;
import com.ssafy.trip.dto.request.MemberRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignDto userSignDto) throws Exception{

        if (memberRepository.findByEmail(userSignDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 아이디 입니다.");
        }

        if (memberRepository.findByName(userSignDto.getName()).isPresent()) {
            throw new Exception("이미 존재하는 이름입니다.");
        }

        Member user = Member.builder()
                .email(userSignDto.getEmail())
                .password(userSignDto.getPassword())
                .name(userSignDto.getName())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        memberRepository.save(user);
    }

    public void modifyMemberInfo(MemberRequestDto memberRequestDto , String userId) {
        Member member = memberRepository.findByEmail(userId).get();
        member.setName(memberRequestDto.getName());

        memberRepository.save(member);
    }

    public void killPassword(String userId) {
        Member member = memberRepository.findByEmail(userId).get();
        member.setPassword("k3i5l7l");

        member.passwordEncode(passwordEncoder);
        memberRepository.save(member);

    }
}
