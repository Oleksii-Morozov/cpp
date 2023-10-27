package model;

import java.time.LocalDate;
import java.time.LocalTime;

public record Deadline(LocalDate date, LocalTime time) {
    @Override
    public String toString() {
        return String.format("%s %s", date, time);
    }
}
