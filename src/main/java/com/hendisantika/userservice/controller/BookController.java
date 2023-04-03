package com.hendisantika.userservice.controller;


import com.hendisantika.userservice.entity.Book;
import com.hendisantika.userservice.exception.ResourceNotFoundException;
import com.hendisantika.userservice.repository.BookRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/Book")
public class BookController {
    @Autowired
    BookRepo bookRepo;

    @GetMapping
    public List<Book> getAllUBooks(){
        return bookRepo.findAllByQuantitiesGreaterThan(0);
    }

    @GetMapping("{authorname}/{namebook}/{id}")
    public Book getbookbyAuthorNameOrBookNameOrIdbook(@PathVariable String authorname,@PathVariable String namebook,@PathVariable String id){
        return bookRepo.findByAuthorNameOrBookNameOrId(
                authorname,
                namebook,
                Long.parseLong(id)
        );
    }
    @PostMapping()
    public void createBook(@RequestBody Book book){
        bookRepo.save(book);
    }
    @PutMapping("{id}")
    public void updateBook(@RequestBody Book book,@PathVariable Long id){
        bookRepo.findById(id).map(book1 -> {
            BeanUtils.copyProperties(book,book1);
            book1.setId(id);
            return bookRepo.save(book1);
        }).orElseThrow(() -> new ResourceNotFoundException("book" , "not found" , id));
    }
    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable String id){
        bookRepo.deleteById(Long.parseLong(id));
    }

}
