package com.ssafy.trip.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "review")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private Integer star;

    @Column(name = "user_id")
    private String userId;
}
