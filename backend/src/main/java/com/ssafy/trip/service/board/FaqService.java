package com.ssafy.trip.service.board;

import com.ssafy.trip.Entity.Faq;
import com.ssafy.trip.repository.board.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FaqService {

    private final FaqRepository faqRepository;

    public void update(Integer articleNo , Faq faq){
        Faq oldfaq = faqRepository.findById(articleNo).orElseThrow(() -> new IllegalArgumentException("해당하는 게시물이 없습니다."));
        faqRepository.save(faq);
    }
}
