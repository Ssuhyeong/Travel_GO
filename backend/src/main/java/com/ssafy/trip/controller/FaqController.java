package com.ssafy.trip.controller;

import com.ssafy.trip.Entity.Faq;
import com.ssafy.trip.repository.board.FaqRepository;
import com.ssafy.trip.service.board.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

    private final FaqRepository faqRepository;
    private final FaqService faqService;

    //전체조회
    @GetMapping
    public ResponseEntity<?> selectAll(){
        List<Faq> faqList = faqRepository.findAll();
        return  new ResponseEntity<>(faqList, HttpStatus.OK);
    }


    // 상세 정보 조회 ( no )
    @GetMapping("/{no}")
    public ResponseEntity<Faq> selectBoardDetail(@PathVariable String no) throws SQLException {

        return new ResponseEntity<Faq>(faqRepository.getArticle(no), HttpStatus.OK);
    }

    // 게시판 등록
    @PostMapping
    public ResponseEntity<Object> registBoard(@RequestBody Faq faq ) throws SQLException {
        faqRepository.registCar(faq.getUserId(), faq.getSubject(), faq.getContent());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<Object> deleteBoard(@PathVariable String no) throws SQLException {
        log.debug("DELETE MAPPING");
        faqRepository.deleteArticle(no);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    // 게시판 내용 수정


    @PostMapping("/update")
    public ResponseEntity<Object> ModifyBoard(@RequestBody Faq faq) throws SQLException {
        Integer faqId = faq.getArticleNo();
        faqService.update(faqId,faq);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
