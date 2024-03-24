package ru.nonoka.bookstopten.service;

import ru.nonoka.bookstopten.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findByYearAndByPage(String year, String column, String sort);
}
