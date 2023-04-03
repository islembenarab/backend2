package com.hendisantika.userservice.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Borrow extends BaseEntity {

    @ManyToOne
    @JoinColumn(name ="id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name ="id_book")
    private Book book;

}
