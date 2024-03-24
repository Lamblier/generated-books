package ru.nonoka.bookstopten.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nonoka.bookstopten.model.Book;
import ru.nonoka.bookstopten.service.BookService;
import ru.nonoka.bookstopten.validation.annotations.ValidColumn;
import ru.nonoka.bookstopten.validation.annotations.ValidSort;
import ru.nonoka.bookstopten.validation.annotations.ValidYear;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping("/top10")
    public List<Book> findAll(
            @ValidYear @RequestParam(required = false) String year,
            @ValidColumn @RequestParam String column,
            @ValidSort @RequestParam String sort) {
        return bookService.findByYearAndByPage(year, column, sort);
    }
}
