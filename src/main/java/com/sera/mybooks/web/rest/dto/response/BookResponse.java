package com.sera.mybooks.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sera.mybooks.domain.Book;
import com.sera.mybooks.domain.enumeration.ReadStatus;

@JsonIgnoreProperties
public class BookResponse {

    Long id;
    String name;
    String author;
    ReadStatus readStatus;

    public BookResponse(Long id, String name, String author, ReadStatus readStatus) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.readStatus = readStatus;
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

    public ReadStatus getReadStatus() {
        return this.readStatus;
    }

    public static BookResponse from(Book book) {
        return new BookResponse(book.getId(), book.getName(), book.getAuthor().getName(), book.getReadStatus());
    }
}
