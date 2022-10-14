package com.cognizant.messengerback.models.DTO;

import com.cognizant.messengerback.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PostDTO {
    int id;
    User user;
    String message;
    String picture;
    Date date;
}
