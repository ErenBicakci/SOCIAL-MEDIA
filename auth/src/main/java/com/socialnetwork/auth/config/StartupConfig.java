package com.socialnetwork.auth.config;


import com.socialnetwork.auth.entity.Role;
import com.socialnetwork.auth.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public StartupConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.findAll().size() == 0){
            Role roleOperator = Role.builder().name("ROLE_USER").build();
            Role roleTeamLeader = Role.builder().name("ROLE_MODERATOR").build();
            Role roleAdmin = Role.builder().name("ROLE_ADMIN").build();

            roleRepository.save(roleAdmin);
            roleRepository.save(roleTeamLeader);
            roleRepository.save(roleOperator);
        }
    }
}
