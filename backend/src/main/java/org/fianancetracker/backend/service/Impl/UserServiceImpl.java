package org.fianancetracker.backend.service.Impl;

import org.fianancetracker.backend.dto.UserDTO;
import org.fianancetracker.backend.models.User;
import org.fianancetracker.backend.repository.UserRepository;
import org.fianancetracker.backend.service.UserService;
import org.fianancetracker.backend.util.MessageVarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String registerUser(UserDTO userDTO) {
        String message = "";
        User user = userRepository.getReferenceByEmail(userDTO.getEmail());
        if (user != null) {
            return MessageVarList.EMAIL_USE;
        } else {
            user = new User();
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPassword(userDTO.getPassword());
            String userName = userDTO.getFirstName().trim().toLowerCase() + userDTO.getLastName().toLowerCase().trim();
            user.setUserName(userName);
            try {
                userRepository.save(user);
            } catch (Exception e) {
                message = e.getMessage();
            }
        }
        return message;
    }


    @Override
    public String loginUser(UserDTO userDTO) {
        String message = "";
        User user = userRepository.getReferenceByEmail(userDTO.getEmail());
        if (user == null) {
            return MessageVarList.NO_USER;
        } else {
            if (!user.getEmail().equals(userDTO.getEmail())) {
                return MessageVarList.INVALID_lOGIN;
            } else if (!(user.getPassword().equals(userDTO.getPassword()))) {
                return MessageVarList.INVALID_lOGIN;
            }
        }
        return message;
    }
}
