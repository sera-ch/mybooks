package com.sera.mybooks.repository;

import com.sera.mybooks.domain.Author;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Author entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findOneByName(String name);
}
