package com.leonardo.repository;

import com.leonardo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
  Role findByRoleName(String roleName);
}
