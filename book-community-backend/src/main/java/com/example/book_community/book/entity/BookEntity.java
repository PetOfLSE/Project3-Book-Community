package com.example.book_community.book.entity;

import com.example.book_community.review.entity.ReviewEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;

    @NotBlank
    private String content;

    @OneToMany(mappedBy = "book")
    private List<ReviewEntity> reviews = new ArrayList<>();

    public void patch(BookEntity entity) {
        if(!this.title.equals(entity.title)) {
            this.title = entity.title;
        }

        if(!this.author.equals(entity.author)) {
            this.author = entity.author;
        }

        if(!this.isbn.equals(entity.isbn)) {
            this.isbn = entity.isbn;
        }

        if(!this.content.equals(entity.content)) {
            this.content = entity.content;
        }
    }
}