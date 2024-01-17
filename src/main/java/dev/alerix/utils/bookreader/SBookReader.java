package dev.alerix.utils.bookreader;

import dev.alerix.models.Book;
import dev.alerix.utils.bookvalidator.BookValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class SBookReader implements BookReader {
    private final BookValidator validator;
    private final SimpleDateFormat dateFormat;

    public SBookReader(BookValidator validator) {
        this.validator = validator;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public List<Book> readBooks(String filePath) {
        List<Book> books = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(line -> {
                if (validator.isValid(line)) {
                    try {
                        String[] details = line.split(",");
                        String title = details[0];
                        String author = details[1];
                        Date publicationDate = dateFormat.parse(details[2]);
                        int circulation = Integer.parseInt(details[3]);
                        books.add(new Book(title, author, publicationDate, circulation));
                    } catch (ParseException | NumberFormatException e) {
                        System.err.println(STR."Error parsing book data: \{line}");
                    }
                } else {
                    System.err.println(STR."Invalid book data format: \{line}");
                }
            });
        } catch (IOException e) {
            System.err.println(STR."Error reading file: \{e.getMessage()}");
        }
        return books;
    }
}
