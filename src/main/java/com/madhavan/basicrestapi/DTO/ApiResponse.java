package com.madhavan.basicrestapi.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T data;
    private ApiError error;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;

    public ApiResponse(T data) {
        this.data = data;
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this.error = error;
        this.timeStamp = LocalDateTime.now();
    }
}
