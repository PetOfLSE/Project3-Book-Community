package com.example.book_community.book.controller;

import com.example.book_community.book.dto.BookDto;
import com.example.book_community.book.entity.BookEntity;
import com.example.book_community.book.service.BookService;
import com.example.book_community.common.page.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController {
    private final BookService bookService;

    @GetMapping("/all")
    public Api<List<BookEntity>> getAllBooks(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return bookService.getAllBooks(pageable);
    }

    @PostMapping("/add")
    public ResponseEntity<com.example.book_community.book.entity.BookEntity> add(@Valid @RequestBody BookDto dto) {
        com.example.book_community.book.entity.BookEntity book = bookService.add(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<com.example.book_community.book.entity.BookEntity> update(@PathVariable Long id, @Valid @RequestBody BookDto dto) {
        com.example.book_community.book.entity.BookEntity book = bookService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        bookService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("도서가 삭제되었습니다.");
    }
}
