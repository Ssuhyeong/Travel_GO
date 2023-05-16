package com.ssafy.trip.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "faq")
@Data
@Entity
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_no")
    private int articleNo;

    @Column(name = "user_id")
    private String userId;
    private String subject;
    private String content;
    private int hit;

    @Column(name = "register_time")
    private Timestamp registerTime;

}
