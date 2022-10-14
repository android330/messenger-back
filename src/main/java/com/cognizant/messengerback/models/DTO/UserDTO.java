package com.cognizant.messengerback.models.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    String username;
    String password;
}
