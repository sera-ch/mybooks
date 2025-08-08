package com.sera.mybooks.web.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sera.mybooks.domain.Book;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties
public class BooksResponse {

    int count;
    List<BookResponse> books;

    public int getCount() {
        return this.count;
    }

    public List<BookResponse> getBooks() {
        return this.books;
    }

    public BooksResponse(List<Book> books) {
        this.count = books.size();
        this.books = books.stream().map(BookResponse::from).collect(Collectors.toList());
    }
}
