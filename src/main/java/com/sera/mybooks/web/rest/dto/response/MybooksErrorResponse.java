package com.sera.mybooks.web.rest.dto.response;

public class MybooksErrorResponse {

    private int code;
    private String message;

    public MybooksErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
