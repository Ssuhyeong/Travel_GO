package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.Entity.Heart;
import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.repository.HeartRepository;
import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.attracion.AttractionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository likeRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    @Transactional
    public boolean addLike(Integer attractionId , String userId) throws Exception{

        Member member = memberRepository.findByEmail(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다"));
        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new Exception("해당하는 관광지를 찾을 수 없습니다."));

        //중복 좋아요 방지
        if(isNotAlreadyLike(member, attraction)) {
            likeRepository.save(new Heart(attraction, member));
            attractionRepository.updateLikeCount(attraction);
            System.out.println("좋아요 카운트: "+ attraction.getLike_count());
            return true;
        }

        return false;

    }

    @Transactional
    public void deleteLike(Integer attractionId , String userId) throws Exception{
        Member member = memberRepository.findByEmail(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다"));
        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new Exception("해당하는 관광지를 찾을 수 없습니다."));

        likeRepository.delete(new Heart(attraction, member));

        attractionRepository.updateDownLike(attraction);


    }

    //사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(Member member, Attraction attraction) {
        return likeRepository.findByMemberAndAttraction(member, attraction).isPresent();
    }

}
