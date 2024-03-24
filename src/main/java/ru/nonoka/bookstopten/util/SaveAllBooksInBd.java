package ru.nonoka.bookstopten.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nonoka.bookstopten.model.Book;
import ru.nonoka.bookstopten.repository.BookRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class SaveAllBooksInBd {

    private final BookRepository bookRepository;
    private final GeneratorJsonBook generatorJsonBook;

    @PostConstruct
    private void saveAllBooksDateBase() {
        List<Book> books = generatorJsonBook.generatedListBooks();
        bookRepository.saveAll(books);
    }
}
