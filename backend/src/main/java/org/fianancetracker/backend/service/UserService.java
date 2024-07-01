package org.fianancetracker.backend.service;

import org.fianancetracker.backend.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    String registerUser(UserDTO userDTO);

    String loginUser(UserDTO userDTO);
}
