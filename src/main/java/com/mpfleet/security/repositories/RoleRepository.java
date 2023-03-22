package com.mpfleet.security.repositories;

import com.mpfleet.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM roles WHERE id NOT IN (SELECT role_id FROM user_role WHERE user_id = ?1)", nativeQuery = true)
    List<Role> getUserNotRoles(Long userId);
}
