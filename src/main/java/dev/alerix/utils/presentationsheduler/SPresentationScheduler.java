package dev.alerix.utils.presentationsheduler;

import dev.alerix.models.Book;

import java.util.*;

public class SPresentationScheduler implements PresentationScheduler {
    @Override
    public Map<String, Date> schedulePresentations(
            Map<String, List<Book>> booksByAuthor,
            boolean showAuthorsWhichWillNotPresent
    ) {
        Map<String, Date> presentationSchedule = new LinkedHashMap<>();
        List<String> authorsWhichWillNotPresent = new ArrayList<>();
        List<Date> availableDates = createPresentationDates();

        Iterator<Date> dateIterator = availableDates.iterator();
        for (Map.Entry<String, List<Book>> authorEntry : booksByAuthor.entrySet()) {
            if (dateIterator.hasNext()) {
                presentationSchedule.put(authorEntry.getKey(), dateIterator.next());
            } else {
                authorsWhichWillNotPresent.add(authorEntry.getKey());
            }
        }

        if(showAuthorsWhichWillNotPresent) {
            authorsWhichWillNotPresent.forEach(author -> System.out.println(STR."Author \{author} will not present"));
        }

        return presentationSchedule;
    }

    private List<Date> createPresentationDates() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JANUARY, 1, 8, 0); // Starting from 1st January 2024, 8:00 AM
        List<Date> dates = new ArrayList<>();
        for (int day = 0; day < 1; day++) { // for 1 day
            for (int hour = 0; hour < 7; hour++) { // from 8:00 AM to 2:00 PM (6 hours)
                dates.add(calendar.getTime());
                calendar.add(Calendar.HOUR_OF_DAY, 1);
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 8); // reset to 8:00 AM for the next day
        }
        return dates;
    }
}

