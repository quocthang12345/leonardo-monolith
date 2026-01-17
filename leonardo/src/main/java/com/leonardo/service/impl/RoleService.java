package com.leonardo.service.impl;

import com.leonardo.entity.Role;
import com.leonardo.repository.RoleRepository;
import com.leonardo.service.IRoleService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
  private final RoleRepository roleRepo;

  @Override
  public Role findByRoleName(String roleName) {
    return Optional.ofNullable(roleName).map(role -> roleRepo.findByRoleName(role)).orElse(null);
  }
}
