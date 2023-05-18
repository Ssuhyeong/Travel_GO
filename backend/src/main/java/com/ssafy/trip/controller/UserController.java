package com.ssafy.trip.controller;

import com.ssafy.trip.repository.UserRepository;
import com.ssafy.trip.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

//    private final UserRepository userRepository;
//    private final MemberService memberService;

    // 로그인
//    @PostMapping("/login")
//    public ResponseEntity<Object> login(@RequestBody User user, HttpSession session) throws SQLException {
//        User userInfo = userRepository.login(user.getId(), user.getPassword());
//        if(userInfo != null) {
//            session.setAttribute("userinfo", userInfo);
//
//            Cookie cookie = new Cookie("ssafy_id", userInfo.getId());
//            cookie.setPath("/");
//            cookie.setMaxAge(60*60*24*365*40);
//
//            return new ResponseEntity<Object>(HttpStatus.OK);
//        }
//        return null;
//    }

//    //로그인 ver.2
//    @PostMapping("/login")
//    public TokenInfo login(@RequestBody LoginRequestDto loginRequestDto){
//        String userId = loginRequestDto.getId();
//        String password = loginRequestDto.getPassword();
//
//        TokenInfo tokenInfo = userService.userLogin(userId,password);
//
//        return tokenInfo;
//    }
//
//    // 로그아웃
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "/";
//    }
//
//    // 회원가입
//    @PostMapping("/join")
//    public ResponseEntity<Object> regist(@RequestBody User user) throws SQLException {
//        userRepository.regist(user.getId(), user.getPassword(), user.getName());
//        return new ResponseEntity<Object>(HttpStatus.OK);
//    }
//
//    // 유저 정보 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> delete(@PathVariable String id) throws SQLException {
//        userRepository.delete(id);
//        return new ResponseEntity<Object>(HttpStatus.OK);
//    }
//
//    // 유저 정보 수정
//    @PostMapping("/update")
//    public ResponseEntity<Object> update(@RequestBody User user, String start_id) throws SQLException {
//        userRepository.update(user.getId(), user.getPassword(), user.getName(), start_id);
//        return new ResponseEntity<Object>(HttpStatus.OK);
//    }
//
//    // 상세 정보 조회 ( id )
//    @GetMapping("/{id}")
//    public ResponseEntity<User> searchbyid(@PathVariable String id) throws SQLException {
//        return new ResponseEntity<User>(userRepository.searchbyid(id), HttpStatus.OK);
//    }
//
//    // 프로필
//    @GetMapping("/profile/{name}")
//    public ResponseEntity<User> profile(@PathVariable String name) throws SQLException {
//        return new ResponseEntity<User>(userRepository.profile(name), HttpStatus.OK);
//    }

}
