package com.telnet.jukebox.spring.repository;

import com.telnet.jukebox.spring.model.Role;
import com.telnet.jukebox.spring.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
