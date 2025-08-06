package com.sera.mybooks.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class AuthorNotFoundErrorResponse extends MybooksErrorResponse {

    private String name;

    public AuthorNotFoundErrorResponse(int code, String message, String name) {
        super(code, message);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
