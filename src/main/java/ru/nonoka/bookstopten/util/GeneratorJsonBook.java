package ru.nonoka.bookstopten.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nonoka.bookstopten.model.Book;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class GeneratorJsonBook {

    @Value("${app.path}")
    private String fileName;

    public List<Book> generatedListBooks() {

        List<Book> books = new ArrayList<>();
        try {
            File file = new File(fileName);

            MappingIterator<Map<String, String>> mappingIterator = new CsvMapper()
                    .enable(CsvParser.Feature.SKIP_EMPTY_LINES)
                    .enable(CsvParser.Feature.TRIM_SPACES)
                    .enable(CsvParser.Feature.WRAP_AS_ARRAY)
                    .enable(CsvParser.Feature.INSERT_NULLS_FOR_MISSING_COLUMNS)
                    .readerFor(Map.class)
                    .with(CsvSchema.emptySchema().withHeader())
                    .readValues(file);

            while (mappingIterator.hasNext()) {
                Map<String, String> row = mappingIterator.next();

                Book book = new Book(
                        Long.valueOf(row.get("")),
                        row.get("isbn"),
                        row.get("title"),
                        row.get("series_title"),
                        row.get("series_release_number"),
                        row.get("authors"),
                        row.get("publisher"),
                        row.get("language"),
                        row.get("description"),
                        checkExtraCharactersLongField(row),
                        row.get("format"),
                        Collections.singletonList(row.get("genres")),
                        checkDateField(row),
                        checkExtraCharactersDoubleField(row, "rating_score"),
                        checkExtraCharactersDoubleField(row, "num_ratings"),
                        checkExtraCharactersDoubleField(row, "num_reviews"),
                        checkExtraCharactersDoubleField(row, "current_readers"),
                        checkExtraCharactersDoubleField(row, "want_to_read"),
                        checkExtraCharactersDoubleField(row, "price"),
                        row.get("url")
                );

                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Double checkExtraCharactersDoubleField(Map<String, String> row, String nameField) {
        Double field;
        try {
            return Double.parseDouble(row.get(nameField));
        } catch (NumberFormatException e) {
            System.out.println("Error parsing %s: ".formatted(nameField) + e.getMessage());
            field = null;
        }
        return field;
    }

    private Long checkExtraCharactersLongField(Map<String, String> row) {
        Long field;
        try {
            return Long.parseLong(row.get("num_pages"));
        } catch (NumberFormatException e) {
            System.out.println("Error parsing num_pages: " + e.getMessage());
            field = null;
        }
        return field;
    }

    private LocalDate checkDateField(Map<String, String> row) {
        String dateStr = row.get("publication_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, formatter);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            date = null;
        }
        return date;
    }

}
