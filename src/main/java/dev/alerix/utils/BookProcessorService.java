package dev.alerix.utils;

import dev.alerix.models.Book;

import java.util.*;
import java.util.stream.Collectors;

public class BookProcessorService {
    public static Map<String, List<Book>> groupAndSortBooks(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::publicationDate))
                .collect(Collectors.groupingBy(Book::author));
    }
}
