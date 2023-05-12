package com.ssafy.trip.Entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Board {

	@Id
	@GeneratedValue
	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private int hit;

	@Column(name = "register_time")
	private Timestamp registerTime;

}
