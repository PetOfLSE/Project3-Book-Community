package com.example.book_community.book.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String content;

    private List<com.example.book_community.book.entity.BookEntity> reviews = new ArrayList<>();

}
