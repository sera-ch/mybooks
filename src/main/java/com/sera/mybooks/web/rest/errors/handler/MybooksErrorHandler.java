package com.sera.mybooks.web.rest.errors.handler;

import com.sera.mybooks.web.rest.AuthorResource;
import com.sera.mybooks.web.rest.BookResource;
import com.sera.mybooks.web.rest.dto.response.*;
import com.sera.mybooks.web.rest.errors.AuthorAlreadyExistsException;
import com.sera.mybooks.web.rest.errors.AuthorNotFoundException;
import com.sera.mybooks.web.rest.errors.BookAlreadyExistsException;
import com.sera.mybooks.web.rest.errors.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = { BookResource.class, AuthorResource.class })
public class MybooksErrorHandler {

    @ExceptionHandler(BookNotFoundException.class)
    ResponseEntity<BookNotFoundErrorResponse> handleBookNotFoundException(BookNotFoundException exception) {
        return ResponseEntity
            .status(MybooksErrorCode.BOOK_NOT_FOUND.getStatus())
            .body(
                new BookNotFoundErrorResponse(
                    MybooksErrorCode.BOOK_NOT_FOUND.getCode(),
                    MybooksErrorCode.BOOK_NOT_FOUND.getMessage(),
                    exception.getName(),
                    exception.getAuthor()
                )
            );
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    ResponseEntity<BookAlreadyExistsErrorResponse> handleBookAlreadyExistsException(BookAlreadyExistsException exception) {
        return ResponseEntity
            .status(MybooksErrorCode.BOOK_ALREADY_EXISTS.getStatus())
            .body(
                new BookAlreadyExistsErrorResponse(
                    MybooksErrorCode.BOOK_ALREADY_EXISTS.getCode(),
                    MybooksErrorCode.BOOK_ALREADY_EXISTS.getMessage(),
                    exception.getName(),
                    exception.getAuthor()
                )
            );
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    ResponseEntity<AuthorNotFoundErrorResponse> handleAuthorNotFoundException(AuthorNotFoundException exception) {
        return ResponseEntity
            .status(MybooksErrorCode.AUTHOR_NOT_FOUND.getStatus())
            .body(
                new AuthorNotFoundErrorResponse(
                    MybooksErrorCode.AUTHOR_NOT_FOUND.getCode(),
                    MybooksErrorCode.AUTHOR_NOT_FOUND.getMessage(),
                    exception.getName()
                )
            );
    }

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    ResponseEntity<AuthorAlreadyExistsErrorResponse> handleAuthorNotFoundException(AuthorAlreadyExistsException exception) {
        return ResponseEntity
            .status(MybooksErrorCode.AUTHOR_ALREADY_EXISTS.getStatus())
            .body(
                new AuthorAlreadyExistsErrorResponse(
                    MybooksErrorCode.AUTHOR_ALREADY_EXISTS.getCode(),
                    MybooksErrorCode.AUTHOR_ALREADY_EXISTS.getMessage(),
                    exception.getName()
                )
            );
    }
}
