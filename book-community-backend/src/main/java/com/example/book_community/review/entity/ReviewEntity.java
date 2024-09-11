package com.example.book_community.review.entity;

import com.example.book_community.book.entity.BookEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    @ToString.Exclude
    private BookEntity book;
}
