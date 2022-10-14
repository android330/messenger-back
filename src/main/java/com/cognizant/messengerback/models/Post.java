package com.cognizant.messengerback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private String message;
    private String picture;

    private Date date;

    public Post(int userId, String message, String picture, Date date) {
        this.userId = userId;
        this.message = message;
        this.picture = picture;
        this.date = date;
    }
}
