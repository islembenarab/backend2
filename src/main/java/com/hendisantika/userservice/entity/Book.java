package com.hendisantika.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
public class Book extends BaseEntity implements Serializable {

    @Column(nullable = false,unique = true)
    private String bookName;
    private String authorName;
    private int quantities;
    private int createdYear;
    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, targetEntity = Borrow.class)
    Set Borrow = new HashSet();

}
