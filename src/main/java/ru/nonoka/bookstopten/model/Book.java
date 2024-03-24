package ru.nonoka.bookstopten.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Book {

    @Id
    public Long id;
    private String isbn;
    private String book;
    private String seriesTitle;
    private String seriesReleaseNumber;
    private String author;
    private String publisher;
    private String language;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Long numPages;
    private String format;

    @ElementCollection(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<String> genres;
    private LocalDate publicationDate;
    private Double rating;
    private Double numRatings;
    private Double numReviews;
    private Double currentReaders;
    private Double wantToRead;
    private Double price;
    private String url;
}
