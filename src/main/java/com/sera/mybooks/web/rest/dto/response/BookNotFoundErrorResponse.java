package com.sera.mybooks.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class BookNotFoundErrorResponse extends MybooksErrorResponse {

    private String name;
    private String author;

    public BookNotFoundErrorResponse(int code, String message, String name, String author) {
        super(code, message);
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }
}
