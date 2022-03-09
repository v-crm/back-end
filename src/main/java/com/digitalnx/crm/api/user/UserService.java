package com.digitalnx.crm.api.user;

import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.userrole.UserRole;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    UserRole saveUserRole(UserRole role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
