package com.folaukaveinga.role;

import java.util.List;

import com.folaukaveinga.user.User;

public interface RoleService {

	List<Role> getByUserId(Long userId);
}
