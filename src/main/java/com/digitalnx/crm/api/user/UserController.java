package com.digitalnx.crm.api.user;

import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.userrole.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/role/save")
    public ResponseEntity<UserRole> saveRole(@RequestBody UserRole userRole) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUserRole(userRole));
    }

    @PostMapping("/role/assign_to_user")
    public ResponseEntity<?> saveRole(@RequestBody String username, @RequestBody String role) {
        userService.addRoleToUser(username, role);
        return ResponseEntity.ok().build();
    }
}
