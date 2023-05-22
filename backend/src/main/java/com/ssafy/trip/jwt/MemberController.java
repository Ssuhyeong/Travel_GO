package com.ssafy.trip.jwt;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.dto.UserSignDto;
import com.ssafy.trip.dto.request.MemberRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.service.JwtService;
import com.ssafy.trip.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @CrossOrigin
    @PostMapping("/sign-up")
    public ResponseEntity<?> userSignUp(@RequestBody UserSignDto userSignDto) throws Exception {
        memberService.signUp(userSignDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> userDetailView(Authentication authentication) {
        String userId = authentication.getName();
        Member member = memberRepository.findByEmail(userId).get();
        String userName = member.getName();

        String[] userInfo = {userId,userName};

        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @PutMapping ("/modify")
    public ResponseEntity<?> userModifyInfo(
            @RequestBody MemberRequestDto memberRequestDto,
            Authentication authentication) {
        String userId = authentication.getName();

        memberService.modifyMemberInfo(memberRequestDto,userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> userDeleteInfo(Authentication authentication) {
        String userId = authentication.getName();
        memberService.killPassword(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
