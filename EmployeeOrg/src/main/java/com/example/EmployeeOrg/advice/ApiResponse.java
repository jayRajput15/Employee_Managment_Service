package com.example.EmployeeOrg.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;

    private T data;

    private ApiErrors apiErrors;

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(ApiErrors apiErrors) {
        this();
        this.apiErrors = apiErrors;
    }
}
