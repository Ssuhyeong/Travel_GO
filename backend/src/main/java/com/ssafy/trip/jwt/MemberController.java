package com.ssafy.trip.jwt;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Photo;
import com.ssafy.trip.dto.UserSignDto;
import com.ssafy.trip.dto.request.MemberRequestDto;
import com.ssafy.trip.dto.request.PhotoRequestDto;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.PhotoRepository;
import com.ssafy.trip.service.JwtService;
import com.ssafy.trip.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PhotoRepository photoRepository;

    @CrossOrigin
    @PostMapping("/sign-up")
    public ResponseEntity<?> userSignUp(@RequestBody UserSignDto userSignDto) throws Exception {
        memberService.signUp(userSignDto);

        PhotoRequestDto photoRequestDto = new PhotoRequestDto();
        photoRequestDto.setMember(memberRepository.findByEmail(userSignDto.getEmail()).get());
        Photo photo = photoRequestDto.toEntity();

        photoRepository.save(photo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> userDetailView(Authentication authentication) {
        String userId = authentication.getName();
        Member member = memberRepository.findByEmail(userId).get();
        String userName = member.getName();


        Photo photo = photoRepository.findByMember(member).get();
        String filePath = photo.getFilePath();

        String[] userInfo = {userId,userName,filePath, String.valueOf(member.getRole())};

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

    @PostMapping("/photo")
    public ResponseEntity<?> uploadProfilePhoto(
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            Authentication authentication
    ) throws Exception{

        log.info("file data -> {}", files.get(0));
        Member member = memberRepository.findByEmail(authentication.getName()).get();
        memberService.savePhoto(member,files);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<?> changePassword(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        String userId = memberRequestDto.getEmail();
        if(!memberRepository.findByEmail(userId).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            memberService.changePassword(userId,memberRequestDto);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
