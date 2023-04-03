package com.hendisantika.userservice.repository;

import com.hendisantika.userservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository <Book,Long > {
    public Book findByAuthorNameOrBookNameOrId(String Authorbook,String bookname,Long Id);
    public List<Book> findAllByQuantitiesGreaterThan(int quantities);
}
