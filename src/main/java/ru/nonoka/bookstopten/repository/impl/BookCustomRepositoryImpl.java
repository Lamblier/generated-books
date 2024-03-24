package ru.nonoka.bookstopten.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import ru.nonoka.bookstopten.model.Book;
import ru.nonoka.bookstopten.repository.BookCustomRepository;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {
    private final EntityManager entityManager;

    @Override
    public List<Book> findByYearAndByPage(String year, String column, String sort) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Predicate nullCheckPredicate = cb.isNotNull(book.get(column));

        if (year != null) {
            Date yearDate = Date.valueOf(year + "-01-01");
            Predicate publicationDatePredicate
                    = cb.equal(cb.function("date_trunc", Date.class, cb.literal("year"),
                    book.get("publicationDate")), yearDate);

            cq.where(publicationDatePredicate, nullCheckPredicate);
        } else {
            cq.where(nullCheckPredicate);
        }

        if ("asc".equalsIgnoreCase(sort)) {
            cq.orderBy(cb.asc(book.get(column)));
        } else {
            cq.orderBy(cb.desc(book.get(column)));
        }

        var query = entityManager.createQuery(cq)
                .setMaxResults(10);

        return query.getResultList();
    }
}
