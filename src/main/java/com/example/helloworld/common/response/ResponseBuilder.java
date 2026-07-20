package com.example.helloworld.common.response;

import org.springframework.data.domain.Page;

import com.example.helloworld.common.constants.AppConstants;

public class ResponseBuilder {

    public static <T> ApiResponse<T> success(T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(AppConstants.SUCCESS)
                .data(data)
                .build();

    }

    public static <T> ApiResponse<T> success(
            String message,
            T data
    ) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();

    }

    public static ApiResponse<Void> success(
            String message
    ) {

        return ApiResponse.<Void>builder()
                .success(true)
                .message(message)
                .build();

    }

    public static <T> ApiResponse<T> page(
            Page<T> page
    ) {

        return ApiResponse.<T>builder()
                .success(true)
                .message("Success")
                .data((T) page.getContent())
                .pagination(
                        Pagination.builder()
                                .page(page.getNumber())
                                .size(page.getSize())
                                .totalElements(page.getTotalElements())
                                .totalPages(page.getTotalPages())
                                .hasNext(page.hasNext())
                                .hasPrevious(page.hasPrevious())
                                .build()
                )
                .build();

    }

}