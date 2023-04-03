package com.hendisantika.userservice.repository;

import com.hendisantika.userservice.entity.Role;
import com.hendisantika.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : user-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/10/20
 * Time: 22.46
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByEmailOrId(String email,Long id);
    List<User> findAllByRoles(Role role);
    boolean existsByEmail(String email);
}
