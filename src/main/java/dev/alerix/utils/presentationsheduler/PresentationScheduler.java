package dev.alerix.utils.presentationsheduler;

import dev.alerix.models.Book;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PresentationScheduler {
    Map<String, Date> schedulePresentations(
            Map<String, List<Book>> booksByAuthor,
            boolean showAuthorsWhichWillNotPresent);
}

