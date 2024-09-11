package com.example.book_community.common.page;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pagination {

    private Integer page;

    private Integer size;

    private Integer total;

    private Integer element;

    private Integer totalElement;
}
