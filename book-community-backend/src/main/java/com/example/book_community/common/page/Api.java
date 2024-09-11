package com.example.book_community.common.page;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Api<T> {
    private T body;

    private Pagination pagination;
}
