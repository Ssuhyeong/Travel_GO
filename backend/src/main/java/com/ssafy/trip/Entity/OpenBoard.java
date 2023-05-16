package com.ssafy.trip.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private Timestamp registerTime;

//    @OneToMany(mappedBy = "user")
//    private List<User> user = new ArrayList<>();

}
