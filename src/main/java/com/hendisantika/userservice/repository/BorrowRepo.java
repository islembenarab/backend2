package com.hendisantika.userservice.repository;

import com.hendisantika.userservice.entity.Book;
import com.hendisantika.userservice.entity.Borrow;
import com.hendisantika.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepo extends JpaRepository <Borrow,Long > {
    public Borrow findByIdOrBookOrUser(Long Id, Book book, User user);
}
