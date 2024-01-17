package dev.alerix.utils.bookreader;

import dev.alerix.models.Book;

import java.util.List;

public interface BookReader {
    List<Book> readBooks(String filePath);
}
