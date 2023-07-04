package com.socialnetwork.auth.repository;


import com.socialnetwork.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    List<Role> findAllByName(String name);
    Role findByName(String name);

}
