package com.example.commons.response;


import com.example.commons.models.GenericResponse;

import java.time.LocalDateTime;

public class ResponseBuilder {

    public static <T> GenericResponse<T> success(T data, String message){
        return GenericResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> GenericResponse<T> success(T data){
        return success(data,"✅ Request processed successfully");
    }

    public static <T> GenericResponse<T> failure(String message) {
        return GenericResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
