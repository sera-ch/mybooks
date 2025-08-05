package com.sera.mybooks.web.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sera.mybooks.domain.Book;

@JsonIgnoreProperties
public class BookRequest {

    String name;
    String author;

    public BookRequest(String name, String author) {
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
