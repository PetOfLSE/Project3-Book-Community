package com.example.book_community.review.service;

import com.example.book_community.book.entity.BookEntity;
import com.example.book_community.book.repository.BookRepository;
import com.example.book_community.review.dto.ReviewDto;
import com.example.book_community.review.entity.ReviewEntity;
import com.example.book_community.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public Boolean add(Long bookId, ReviewDto reviewDto) {
        Optional<BookEntity> book = bookRepository.findById(bookId);
        if(book.isPresent()) {
            BookEntity bookEntity = book.get();
            ReviewEntity review = reviewDto.toEntity(bookEntity);
            bookEntity.getReviews().add(review);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }
}
