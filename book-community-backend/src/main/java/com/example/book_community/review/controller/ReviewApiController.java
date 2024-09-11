package com.example.book_community.review.controller;

import com.example.book_community.review.dto.ReviewDto;
import com.example.book_community.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewService reviewService;

    @PostMapping("/{book_id}")
    public ResponseEntity<String> add(@PathVariable Long book_id, @RequestBody ReviewDto reviewDto) {
        Boolean review = reviewService.add(book_id, reviewDto);
        if(review){
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰 작성 완료!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("리뷰 작성 실패");
        }
    }
}
