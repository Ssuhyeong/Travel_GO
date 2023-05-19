package com.ssafy.trip.jwt;

import com.ssafy.trip.dto.UserSignDto;
import com.ssafy.trip.service.JwtService;
import com.ssafy.trip.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final JwtService jwtService;
    private final MemberService memberService;

    @CrossOrigin
    @PostMapping("/sign-up")
    public ResponseEntity<?> userSignUp(@RequestBody UserSignDto userSignDto) throws Exception {
        memberService.signUp(userSignDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
