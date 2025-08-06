package com.sera.mybooks.repository;

import com.sera.mybooks.domain.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT COUNT(*) FROM Book b INNER JOIN Author a ON b.author.id = a.id WHERE b.name = :name AND a.name = :author")
    Long countByNameAndAuthor(String name, String author);
}
