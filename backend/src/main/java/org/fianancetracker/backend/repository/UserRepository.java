package org.fianancetracker.backend.repository;

import org.fianancetracker.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getReferenceByEmail(String email);

    User findByUserName(String username);
}
