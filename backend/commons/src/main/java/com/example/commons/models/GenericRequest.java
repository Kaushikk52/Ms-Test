package com.example.commons.models;

import lombok.Data;

import java.util.List;
import java.util.Map;

public class GenericRequest<T> {

    private T data;

    private List<T> bulkData;

    private SearchRequest search;

    private Map<String,Object> metadata;

    @Data
    public static class SearchRequest{
        private String field;      // Entity field
        private String operator;   // eq, ne, like, gt, lt, gte, lte, in
        private Object value;      // Value
    }

    @Data
    public static class SortRequest{
        private String field;
        private String direction;  // asc or desc
    }
}
