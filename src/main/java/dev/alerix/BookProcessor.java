package dev.alerix;

import dev.alerix.models.Book;
import dev.alerix.utils.BookProcessorService;
import dev.alerix.utils.bookreader.BookReader;
import dev.alerix.utils.bookreader.SBookReader;
import dev.alerix.utils.bookvalidator.BookValidator;
import dev.alerix.utils.bookvalidator.RegexBookValidator;
import dev.alerix.utils.presentationsheduler.PresentationScheduler;
import dev.alerix.utils.presentationsheduler.SPresentationScheduler;

import java.util.List;
import java.util.Map;

public class BookProcessor {
    private static final String filePath = "src/main/resources/books.txt";
    private static final boolean showAuthorsWhichWillNotPresent = true;

    public static void start() {
        BookValidator validator = new RegexBookValidator();
        BookReader reader = new SBookReader(validator);
        PresentationScheduler scheduler = new SPresentationScheduler();

        List<Book> books = reader.readBooks(filePath);

        Map<String, List<Book>> booksByAuthor = BookProcessorService.groupAndSortBooks(books);

        scheduler.schedulePresentations(booksByAuthor, showAuthorsWhichWillNotPresent)
                .forEach((author, date) -> System.out.println(STR."\{author} - \{date}"));

        System.out.println(STR."Total books: \{books.size()}");
    }
}
