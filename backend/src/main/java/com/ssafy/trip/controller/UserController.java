package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.User;
import com.ssafy.trip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpSession session) throws SQLException {
        User userInfo = userRepository.login(user.getId(), user.getPassword());
        if(userInfo != null) {
            session.setAttribute("userinfo", userInfo);

            Cookie cookie = new Cookie("ssafy_id", userInfo.getId());
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*365*40);

            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return null;
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/";
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<Object> regist(@RequestBody User user) throws SQLException {
        userRepository.regist(user.getId(), user.getPassword(), user.getName());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 유저 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) throws SQLException {
        userRepository.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 유저 정보 수정
    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody User user, String start_id) throws SQLException {
        userRepository.update(user.getId(), user.getPassword(), user.getName(), start_id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 상세 정보 조회 ( id )
    @GetMapping("/{id}")
    public ResponseEntity<User> searchbyid(@PathVariable String id) throws SQLException {
        return new ResponseEntity<User>(userRepository.searchbyid(id), HttpStatus.OK);
    }

    // 프로필
    @GetMapping("/profile/{name}")
    public ResponseEntity<User> profile(@PathVariable String name) throws SQLException {
        return new ResponseEntity<User>(userRepository.profile(name), HttpStatus.OK);
    }

}
