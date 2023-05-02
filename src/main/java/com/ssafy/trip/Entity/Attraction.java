package com.ssafy.trip.Entity;

import lombok.Data;

import javax.persistence.*;

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

}
