package ru.nonoka.bookstopten.repository;

import org.springframework.stereotype.Repository;
import ru.nonoka.bookstopten.model.Book;

import java.util.List;

@Repository
public interface BookCustomRepository {
    List<Book> findByYearAndByPage(String year, String column, String sort);
}
