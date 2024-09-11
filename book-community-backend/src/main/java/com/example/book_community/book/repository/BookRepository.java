package com.example.book_community.book.repository;

import com.example.book_community.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
