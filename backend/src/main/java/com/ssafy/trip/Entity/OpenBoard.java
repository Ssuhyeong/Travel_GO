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
    @Column(name = "id")
    private int id;

    private String title;
    private String content;
    private int hit;
//
//    @OneToMany(mappedBy = "user")
//    private List<User> user = new ArrayList<>();

    @Column(name = "user_id")
    private String userId;

    @Column(name = "register_time")
    private Timestamp registerTime;
}
