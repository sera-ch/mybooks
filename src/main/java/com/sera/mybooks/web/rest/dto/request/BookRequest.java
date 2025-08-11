package com.sera.mybooks.web.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.mybooks.domain.Book;
import com.sera.mybooks.domain.enumeration.ReadStatus;

@JsonIgnoreProperties
public class BookRequest {

    String name;
    String author;

    @JsonProperty(value = "status", defaultValue = "WISHLIST")
    ReadStatus readStatus;

    public BookRequest(String name, String author, ReadStatus readStatus) {
        this.name = name;
        this.author = author;
        this.readStatus = readStatus;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public ReadStatus getReadStatus() {
        return readStatus;
    }
}
