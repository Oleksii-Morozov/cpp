package dev.alerix.utils.bookvalidator;

public class RegexBookValidator implements BookValidator {

    /*
    ^ - start of the string
    (.+) - any character one or more times (title)
    , - comma
    (.+) - any character one or more times (author)
    , - comma
    (\\d{2}\\.\\d{2}\\.\\d{4}) - date in format dd.MM.yyyy (publication date)
    , - comma
    (\\d+) - any digit one or more times (circulation)
     */

    private static final String BOOK_PATTERN = "^(.+),(.+),(\\d{2}\\.\\d{2}\\.\\d{4}),(\\d+)$";

    @Override
    public boolean isValid(String bookData) {
        return bookData.matches(BOOK_PATTERN);
    }
}
