package com.ssafy.trip.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String registerTime;

    @PrePersist
    void registerTime() {
        Timestamp time = Timestamp.from(Instant.now());
        this.registerTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time);
    }

}
