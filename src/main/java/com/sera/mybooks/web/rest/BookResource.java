package com.sera.mybooks.web.rest;

import com.sera.mybooks.domain.Author;
import com.sera.mybooks.domain.Book;
import com.sera.mybooks.repository.AuthorRepository;
import com.sera.mybooks.repository.BookRepository;
import com.sera.mybooks.web.rest.dto.request.BookRequest;
import com.sera.mybooks.web.rest.dto.request.BookUpdateStatusRequest;
import com.sera.mybooks.web.rest.dto.response.BookResponse;
import com.sera.mybooks.web.rest.dto.response.BooksResponse;
import com.sera.mybooks.web.rest.errors.AuthorNotFoundException;
import com.sera.mybooks.web.rest.errors.BadRequestAlertException;
import com.sera.mybooks.web.rest.errors.BookAlreadyExistsException;
import com.sera.mybooks.web.rest.errors.BookNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sera.mybooks.domain.Book}.
 */
@RestController
@RequestMapping("/api")
@Transactional
@CrossOrigin
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private static final String ENTITY_NAME = "book";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookResource(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * {@code POST  /books} : Create a new book.
     *
     * @param book the book to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new book, or with status {@code 400 (Bad Request)} if the book has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/books")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest book) throws URISyntaxException {
        log.debug("REST request to save Book : {}", book);
        Author author =
            this.authorRepository.findOneByName(book.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(book.getAuthor()));
        Long exists = this.bookRepository.countByNameAndAuthor(book.getName(), book.getAuthor());
        if (exists != 0) {
            throw new BookAlreadyExistsException(book.getName(), book.getAuthor());
        }
        Book result = bookRepository.save(new Book().name(book.getName()).author(author).readStatus(book.getReadStatus()));
        return ResponseEntity
            .created(new URI("/api/books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(BookResponse.from(result));
    }

    /**
     * {@code PUT  /books/:id} : Updates an existing book.
     *
     * @param id the id of the book to save.
     * @param request the {@link BookUpdateStatusRequest}
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BookUpdateStatusRequest request
    ) throws URISyntaxException {
        log.debug("REST request to update Book status : {}, {}", id, request);

        Book book = this.bookRepository.findById(id).orElseThrow();
        book.setReadStatus(request.getStatus());

        BookResponse result = BookResponse.from(bookRepository.save(book));
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, book.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /books/:id} : Partial updates given fields of an existing book, field will ignore if it is null
     *
     * @param id the id of the book to save.
     * @param book the book to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 404 (Not Found)} if the book is not found,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/books/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Book> partialUpdateBook(@PathVariable(value = "id", required = false) final Long id, @RequestBody Book book)
        throws URISyntaxException {
        log.debug("REST request to partial update Book partially : {}, {}", id, book);
        if (book.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, book.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Book> result = bookRepository
            .findById(book.getId())
            .map(existingBook -> {
                if (book.getName() != null) {
                    existingBook.setName(book.getName());
                }
                if (book.getReadStatus() != null) {
                    existingBook.setReadStatus(book.getReadStatus());
                }

                return existingBook;
            })
            .map(bookRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, book.getId().toString())
        );
    }

    /**
     * {@code GET  /books} : get all the books.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of books in body.
     */
    @GetMapping("/books")
    public ResponseEntity<BooksResponse> getAllBooks() {
        log.debug("REST request to get all Books");
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(new BooksResponse(books));
    }

    /**
     * {@code GET  /books/:id} : get the "id" book.
     *
     * @param id the id of the book to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the book, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        log.debug("REST request to get Book : {}", id);
        Optional<Book> book = bookRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(book.map(BookResponse::from));
    }

    /**
     * {@code DELETE  /books/:id} : delete the "id" book.
     *
     * @param id the id of the book to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.debug("REST request to delete Book : {}", id);
        bookRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code DELETE  /books} : delete all books, for debugging only
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/books")
    public ResponseEntity<Void> deleteBooks() {
        log.debug("REST request to delete all Books");
        this.bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
