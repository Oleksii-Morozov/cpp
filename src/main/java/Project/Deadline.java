package Project;

import java.time.*;

public record Deadline(LocalDate date, LocalTime time) {
    @Override
    public String toString() {
        return date + " " + time;
    }
}
