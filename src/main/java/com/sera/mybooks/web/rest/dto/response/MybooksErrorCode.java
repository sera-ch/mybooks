package com.sera.mybooks.web.rest.dto.response;

import org.springframework.http.HttpStatus;

public enum MybooksErrorCode {
    // Author errors: 100xxx
    AUTHOR_NOT_FOUND(HttpStatus.NOT_FOUND, 100404, "Author is not found"),
    AUTHOR_ALREADY_EXISTS(HttpStatus.CONFLICT, 100409, "Author already exists"),

    // Book error: 200xxx
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, 200404, "Book is not found"),
    BOOK_ALREADY_EXISTS(HttpStatus.CONFLICT, 200409, "Book already exists");

    private HttpStatus status;
    private int code;
    private String message;

    public HttpStatus getStatus() {
        return this.status;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    MybooksErrorCode(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
