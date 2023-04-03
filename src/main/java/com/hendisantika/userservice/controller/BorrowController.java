package com.hendisantika.userservice.controller;


import com.hendisantika.userservice.entity.Book;
import com.hendisantika.userservice.entity.Borrow;
import com.hendisantika.userservice.repository.BookRepo;
import com.hendisantika.userservice.repository.BorrowRepo;
import com.hendisantika.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/borrow")
public class BorrowController {
    @Autowired
    BorrowRepo borrowRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping
    public List<Borrow> getAllUBorrows(){
        return borrowRepo.findAll();
    }

    @GetMapping("{iduser}/{idbook}/{id}")
    public Borrow getborrowbyIdborrowOrBookOrUser(@PathVariable String iduser,@PathVariable String idbook,@PathVariable String id){
        return borrowRepo.findByIdOrBookOrUser(
                Long.parseLong(id),
                        bookRepo.getById(Long.parseLong(idbook)),
                userRepo.getById(Long.parseLong(iduser))
                );
    }
    @PostMapping()
    public void createborrow(@RequestBody Borrow borrow){
        borrowRepo.save(borrow);
        Book book=bookRepo.findById(borrow.getBook().getId()).get();
        book.setQuantities(book.getQuantities()-1);
        bookRepo.save(book);
    }
    @PutMapping("{id}")
    public void updateborrow(@RequestBody Borrow borrow,@PathVariable String id){
        Borrow borrow1= borrowRepo.getById(Long.parseLong(id));
        borrow1.setBook(borrow.getBook());
        borrow1.setUser(borrow.getUser());
        borrowRepo.save(borrow1);
    }
    @DeleteMapping("{id}")
    public void deleteborrow(@PathVariable String id){
        borrowRepo.deleteById(Long.parseLong(id));
    }

}
