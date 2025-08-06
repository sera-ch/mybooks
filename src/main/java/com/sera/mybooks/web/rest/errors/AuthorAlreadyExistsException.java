package com.sera.mybooks.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;

public class AuthorAlreadyExistsException extends AbstractThrowableProblem {

    private String name;

    public AuthorAlreadyExistsException(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
