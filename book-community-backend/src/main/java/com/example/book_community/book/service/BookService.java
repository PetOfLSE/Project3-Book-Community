package com.example.book_community.book.service;

import com.example.book_community.book.dto.BookDto;
import com.example.book_community.book.entity.BookEntity;
import com.example.book_community.book.repository.BookRepository;
import com.example.book_community.common.page.Api;
import com.example.book_community.common.page.Pagination;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public com.example.book_community.book.entity.BookEntity add(@Valid BookDto dto) {
        com.example.book_community.book.entity.BookEntity book = dto.toEntity();

        return bookRepository.save(book);
    }

    public Api<List<BookEntity>> getAllBooks(Pageable pageable) {
       var list = bookRepository.findAll(pageable);
        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .total(list.getTotalPages())
                .element(list.getNumberOfElements())
                .totalElement(list.getNumberOfElements())
                .build();

        var response = Api.<List<BookEntity>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();

        return response;
    }

    public com.example.book_community.book.entity.BookEntity update(Long id, @Valid BookDto dto) {
        Optional<com.example.book_community.book.entity.BookEntity> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            com.example.book_community.book.entity.BookEntity book = byId.get();
            com.example.book_community.book.entity.BookEntity entity = dto.toEntity();
            book.patch(entity);
            return bookRepository.save(book);
        }else{
            throw new RuntimeException("id에 일치하는 도서가 없습니다가");
        }
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
