package com.digitalnx.crm.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public @ResponseBody String addUser(@RequestParam String firstname,
                   @RequestParam String surname,
                   @RequestParam String password,
                   @RequestParam String userRole,
                   @RequestParam String postalAddress,
                   @RequestParam String city,
                   @RequestParam String phoneNumber,
                   @RequestParam int postalCode) {
        User u = new User(firstname, surname, password, UserRole.CUSTOMER, postalAddress, city, phoneNumber, postalCode);
        userRepository.save(u);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> allUsers() {
        return userRepository.findAll();
    }
}
