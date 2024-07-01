package org.fianancetracker.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String cpassword;
}
