package org.fianancetracker.backend.service.Impl;

import org.fianancetracker.backend.models.User;
import org.fianancetracker.backend.repository.UserRepository;
import org.fianancetracker.backend.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUserName(String username) {
        User user = userRepository.findByUserName(username);
        return user;
    }
}
