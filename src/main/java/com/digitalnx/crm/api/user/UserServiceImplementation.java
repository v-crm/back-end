package com.digitalnx.crm.api.user;

import com.digitalnx.crm.api.user.user.User;
import com.digitalnx.crm.api.user.user.UserRepository;
import com.digitalnx.crm.api.user.userrole.UserRole;
import com.digitalnx.crm.api.user.userrole.UserRoleRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) { throw new UsernameNotFoundException("Username not found in the database!"); }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getUserRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public UserServiceImplementation(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserRole saveUserRole(UserRole role) {
        return userRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        UserRole role = userRoleRepository.findByName(roleName);
        user.getUserRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public void addRoleToUser(Integer id, String roleName) {
        Optional<User> userOptional = userRepository.findById(id);
        UserRole role = userRoleRepository.findByName(roleName);
        userOptional.ifPresentOrElse(
                (user) -> {
                        user.getUserRoles().add(role);
                        userRepository.save(user);
                    },
                () -> {
                    throw new RuntimeException("User ID not found.");
                }
        );
    }
    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}