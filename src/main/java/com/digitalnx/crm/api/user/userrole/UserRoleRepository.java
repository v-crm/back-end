package com.digitalnx.crm.api.user.userrole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByName(String name);
}
