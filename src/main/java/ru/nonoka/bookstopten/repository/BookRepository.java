package ru.nonoka.bookstopten.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nonoka.bookstopten.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookCustomRepository {
}
