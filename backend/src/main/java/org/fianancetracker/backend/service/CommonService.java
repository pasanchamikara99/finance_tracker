package org.fianancetracker.backend.service;

import org.fianancetracker.backend.models.User;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {

    User findUserByUserName(String username);
}
