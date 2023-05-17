package com.ssafy.trip.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Table(name = "open_board")
@Data
@Entity
public class OpenBoard {

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

//    @OneToMany(mappedBy = "user")
//    private List<User> user = new ArrayList<>();

}
