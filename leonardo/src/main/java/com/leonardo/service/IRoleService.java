package com.leonardo.service;

import com.leonardo.entity.Role;

public interface IRoleService {
	Role findByRoleName(String roleName);
}
