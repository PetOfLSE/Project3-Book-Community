package com.example.book_community.book.dto;

import lombok.*;
import com.example.book_community.book.entity.BookEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private String title;

    private String author;

    private String isbn;

    private String content;


    public BookEntity toEntity() {
        return BookEntity.builder()
                .title(this.title)
                .author(this.author)
                .isbn(this.isbn)
                .content(this.content)
                .build();
    }
}
