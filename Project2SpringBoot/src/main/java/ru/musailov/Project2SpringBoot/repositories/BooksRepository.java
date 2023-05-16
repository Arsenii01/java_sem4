package ru.musailov.Project2SpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.musailov.Project2SpringBoot.models.Book;

import java.util.List;

/**
 * @author Neil Alishev
 */
@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleStartingWith(String title);
}
