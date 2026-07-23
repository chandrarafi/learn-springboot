package com.example.helloworld.common.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({ "success", "message", "data", "errors", "pagination" })
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T data;

    private Object errors;

    private Pagination pagination;

}