package org.fianancetracker.backend.service;

import org.fianancetracker.backend.dto.UserDTO;

public interface UserService {


    String registerUser(UserDTO userDTO);

    String loginUser(UserDTO userDTO);
}
