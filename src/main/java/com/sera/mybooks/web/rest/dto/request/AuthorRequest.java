package com.sera.mybooks.web.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class AuthorRequest {

    String name;

    public String getName() {
        return this.name;
    }
}
