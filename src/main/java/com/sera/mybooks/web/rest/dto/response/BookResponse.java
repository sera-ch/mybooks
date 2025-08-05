package com.sera.mybooks.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sera.mybooks.domain.Book;

@JsonIgnoreProperties
public class BookResponse {

    Long id;
    String name;
    String author;

    public BookResponse(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public static BookResponse from(Book book) {
        return new BookResponse(book.getId(), book.getName(), book.getAuthor().getName());
    }
}
