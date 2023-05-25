package com.ssafy.trip.dto.request;

import com.ssafy.trip.Entity.Member;
import com.ssafy.trip.Entity.Photo;
import lombok.*;

import java.util.Random;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoRequestDto {

    private Long fileSize;
    private String originalName;
    private String filePath;
    private Member member;

    public String alphaRandom() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public Photo toEntity() {
        Photo photo = Photo.builder()
                .fileSize(9999L)
                .filePath("resources\\static\\img\\photo\\default.jpg")
                .originalName("default.jpg")
                .member(member)
                .build();

        return photo;
    }
}
