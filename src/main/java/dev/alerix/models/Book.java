package dev.alerix.models;

import java.io.Serializable;
import java.util.*;

public record Book(String title, String author, Date publicationDate, int circulation) implements Serializable, Comparable<Book> {
    @Override
    public String toString() {
        return STR.
                """
                {
                    title='\{title}\{'\''},
                    author='\{author}\{'\''},
                    publicationDate=\{publicationDate},
                    circulation=\{circulation}\{'}'
                }
                """;
    }

    @Override
    public int compareTo(Book o) {
        return Comparator.comparing(Book::title)
                .thenComparing(Book::author)
                .thenComparing(Book::publicationDate)
                .thenComparing(Book::circulation)
                .compare(this, o);
    }
}
