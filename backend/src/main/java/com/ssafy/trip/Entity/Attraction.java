package com.ssafy.trip.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "attraction")
public class Attraction {

    @Id
    @GeneratedValue
    private Integer content_id;

    private Integer content_type_id;
    private String title;
    private String addr1;
    private String first_image;
    private int sido_code;
    private String latitude;
    private String longitude;
    private String overview;
    @Column(name = "like_count")
    private Integer likeCount;

    @OneToMany(mappedBy = "attraction" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    List<Review> reviews = new ArrayList<>();

}
