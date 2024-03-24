package ru.nonoka.bookstopten.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nonoka.bookstopten.model.Book;
import ru.nonoka.bookstopten.repository.BookRepository;
import ru.nonoka.bookstopten.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findByYearAndByPage(String year, String column, String sort) {
        return bookRepository.findByYearAndByPage(year, column, sort);
    }
}
