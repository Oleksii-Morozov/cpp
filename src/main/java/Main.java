import dev.alerix.BookProcessor;

/*
The text file contains information about books, including information about
title, author, date of publication, set in the format 01.05.2022, circulation. You are required to
create a collection of films by verifying the data from the file. For verification
use regular expressions.

Using stream.api, create a collection grouped by author,
sorting books by the same author by publication date.

Set a list of dates of book presentations. Create a list of pairs of author - date and time
presentations. Assume that the presentation takes place for 1 hour from 8.00 to 14.00.
Put all authors for whom there are not enough dates in a separate list.
 */

public class Main {
    public static void main(String[] args) {
        BookProcessor.start();
    }
}
