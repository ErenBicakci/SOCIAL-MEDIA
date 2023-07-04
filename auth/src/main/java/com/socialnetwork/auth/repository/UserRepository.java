package com.socialnetwork.auth.repository;

import com.socialnetwork.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    User findByUsernameAndDeletedFalse(String username);

    Optional<User> findUserByUsernameAndDeletedFalse(String username);
    List<User> findAllByDeletedFalse();
}
