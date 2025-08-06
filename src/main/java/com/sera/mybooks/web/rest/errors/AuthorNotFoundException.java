package com.sera.mybooks.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;

public class AuthorNotFoundException extends AbstractThrowableProblem {

    private String name;

    public AuthorNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
