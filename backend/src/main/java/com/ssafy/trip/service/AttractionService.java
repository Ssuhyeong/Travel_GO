package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
//import com.ssafy.trip.repository.LikeRepository;
//import com.ssafy.trip.repository.MemberRepository;
import com.ssafy.trip.repository.attracion.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
//    private final LikeRepository likeRepository;
//    private final MemberRepository userRepository;

    public Page<Attraction> getAttractions(
            Pageable pageable, Integer sidoCode, Integer contentTypeId, String keyword, Integer contentId
    ) {
        return attractionRepository.findBySearchOption(pageable, sidoCode,contentTypeId,keyword, contentId);
    }


//    public boolean attractionLike(String userId, Integer contentId){
//        Attraction attraction = attractionRepository.findById(contentId).orElseThrow(() -> new IllegalArgumentException("Attraction not found!!"));
//        User user = userRepository.findByName(userId).orElseThrow(() -> new UsernameNotFoundException("user is not found!!"));
//
//        Optional<Like> likeCheck = likeRepository.findByUserAndAttraction(userId,contentId);
//
//        // 좋아요 없는 경우 (누를 경우 좋아요 저장)
//        if(isLikeCheck(user,attraction)) {
////            likeRepository.save(new Like(user,attraction));
//            likeRepository.save(Like.builder().attraction(attraction).user(user).build());
//            return true;
//        }
//        else{
//            likeRepository.delete(Like.builder().attraction(attraction).user(user).build());
//            return false;
//        }
//    }
//
//    private boolean isLikeCheck(User user , Attraction attraction) {
//        return likeRepository.findByUserAndAttraction(user.getId(), attraction.getContent_id()).isPresent();
//    }
//
//    public Integer likeCounter(Integer contentId){
//
//        Attraction attractions = attractionRepository.findById(contentId).orElseThrow(() ->new IllegalArgumentException("Attraction not Found !!"));
//        List<Like> likes = likeRepository.findAllByAttraction(attractions);
//
//        return likes.size();
//    }

}
