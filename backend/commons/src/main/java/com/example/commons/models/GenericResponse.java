package com.example.commons.models;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class GenericResponse<T> {

    private boolean success;

    private String type;

    private String message;

    private T data;

    private List<T> dataList;
    
    private LocalDateTime timestamp;

}
