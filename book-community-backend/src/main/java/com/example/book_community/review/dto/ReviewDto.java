package com.example.book_community.review.dto;

import com.example.book_community.book.entity.BookEntity;
import com.example.book_community.review.entity.ReviewEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewDto {

    private String content;

    public ReviewEntity toEntity(BookEntity book) {

        return ReviewEntity.builder()
                .content(this.content)
                .createdAt(LocalDateTime.now())
                .book(book)
                .build();
    }
}
