package com.hendisantika.userservice.controller;

import com.hendisantika.userservice.config.CurrentUser;
import com.hendisantika.userservice.dto.LocalUser;
import com.hendisantika.userservice.entity.Role;
import com.hendisantika.userservice.entity.User;
import com.hendisantika.userservice.repository.RoleRepository;
import com.hendisantika.userservice.repository.UserRepository;
import com.hendisantika.userservice.security.oauth2.user.OAuth2UserInfo;
import com.hendisantika.userservice.service.UserServiceImpl;
import com.hendisantika.userservice.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : user-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/11/20
 * Time: 05.31
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<User> getAllUsers(){
        return userRepository.findAllByRoles(roleRepository.findByName(Role.ROLE_USER));
    }
    @GetMapping("/{email}/{id}")
    public Optional<User> getuserbyEmailorId(@PathVariable String email, @PathVariable Long id){
       if (null!=userService.findUserByEmail(email)){
           return Optional.ofNullable(userService.findUserByEmail(email));
       }else {
           return userService.findUserById(id);
       }
    }
    @PutMapping("/{id}")
    public void updateuser(@RequestBody User user,@PathVariable OAuth2UserInfo id){
        userRepository.save(user);
    }
    @DeleteMapping("/{id}")
    public void deleteuser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
