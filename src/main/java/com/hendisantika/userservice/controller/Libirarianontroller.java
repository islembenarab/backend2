package com.hendisantika.userservice.controller;


import com.hendisantika.userservice.entity.Role;
import com.hendisantika.userservice.entity.User;
import com.hendisantika.userservice.repository.RoleRepository;
import com.hendisantika.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/Librarian")
public class Libirarianontroller {


    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<User> getAllLibrarians(){
        return userRepository.findAllByRoles(roleRepository.findByName(Role.ROLE_LIBRARIAN));
    }

    @GetMapping("/{email}/{id}")
    public User getLibrarianbyUsernameorId(@PathVariable String email,@PathVariable String id){
        return userRepository.findByEmailOrId(
                email,
                Long.parseLong(id)
        );
    }


    @DeleteMapping("/{id}")
    public void deleteLibrarian(@PathVariable String id){
        userRepository.deleteById(Long.parseLong(id));
    }

}
