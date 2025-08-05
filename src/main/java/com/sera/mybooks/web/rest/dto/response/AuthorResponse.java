package com.sera.mybooks.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sera.mybooks.domain.Author;
import com.sera.mybooks.domain.Book;

@JsonIgnoreProperties
public class AuthorResponse {

    String name;

    public AuthorResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static AuthorResponse from(Author author) {
        return new AuthorResponse(author.getName());
    }
}
