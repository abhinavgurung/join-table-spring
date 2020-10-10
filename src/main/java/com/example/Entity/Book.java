package com.example.Entity;


import lombok.Data;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "name")
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private Set<BookPublisher> bookPublishers;

    public Book(String name, BookPublisher... bookPublishers){
        this.name = name;
        for(BookPublisher bookPublisher: bookPublishers) bookPublisher.setBook(this);
        this.bookPublishers = Stream.of(bookPublishers).collect(Collectors.toSet());
    }
}
