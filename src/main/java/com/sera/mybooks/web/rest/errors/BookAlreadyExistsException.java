package com.sera.mybooks.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;

public class BookAlreadyExistsException extends AbstractThrowableProblem {

    private String name;
    private String author;

    public BookAlreadyExistsException(String name, String author) {
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
