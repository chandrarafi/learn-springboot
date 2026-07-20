package com.example.helloworld.common.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

    private boolean hasNext;

    private boolean hasPrevious;

}