package com.ssafy.trip.Entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Table(name = "faq")
@Data
@Entity
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_no")
    private int articleNo;

    private String subject;
    private String content;
    @ColumnDefault(value = "0")
    private Integer hit;

    @Column(name = "register_time")
    private String registerTime;

    @PrePersist
    void registerTime() {
        Timestamp time = Timestamp.from(Instant.now());
        this.registerTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time);
    }

}
