package com.ssafy.trip.service;

import com.ssafy.trip.Entity.Attraction;
import com.ssafy.trip.repository.attracion.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public Page<Attraction> getAttractions(
            Pageable pageable, Integer sidoCode, Integer contentTypeId, String keyword, Integer contentId
    ) {
        return attractionRepository.findBySearchOption(pageable, sidoCode,contentTypeId,keyword, contentId);
    }

    public Page<Attraction> getLikeOrderBy(
            Pageable pageable, String keyword , Integer sidoCode , Integer contentTypeId
    ) {
        return attractionRepository.findBySearchOrderBy(pageable, keyword,sidoCode, contentTypeId);
    }

    public List<Attraction> bestLike() {
        return attractionRepository.findByBestLike();
    }

    public List<Attraction> distance(String keyword, String w , String g) {
        List<Attraction> distanceList = attractionRepository.findByKeyword(keyword);
        Double wee = Double.valueOf(w);
        Double gyu = Double.valueOf(g);

        Map<Double,Attraction> dis = new TreeMap<>();

        for (int i=0;i<distanceList.size();i++){
            Double x = Double.valueOf(distanceList.get(i).getLatitude());
            Double y = Double.valueOf(distanceList.get(i).getLongitude());

            Double result = Math.abs(x-wee)+Math.abs(y-gyu);

            dis.put(result,distanceList.get(i));
        }

        List<Attraction> resultList = new ArrayList<>();

        for (Double key : dis.keySet()){
            Attraction att = dis.get(key);
            resultList.add(att);
        }

        return resultList;
    }

    public List<Attraction> distanceLimit(String keyword, String w , String g) {
        List<Attraction> attractionList = distance(keyword,w,g);

        List<Attraction> limitAttraction = new ArrayList<>();

        if(attractionList.size()<4){
            for(int i=0;i<attractionList.size();i++){
                limitAttraction.add(attractionList.get(i));
            }
        }
        else{
            for (int i=0;i<4;i++){
                limitAttraction.add(attractionList.get(i));
            }
        }

        return limitAttraction;
    }

}
