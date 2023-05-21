package com.ssafy.trip.Entity;

import io.micrometer.core.annotation.Counted;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "attraction")
public class Attraction implements Serializable {

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
    private Integer like_count;

    @ManyToOne(targetEntity = Member.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email",referencedColumnName = "email")
    private Member member;


}
