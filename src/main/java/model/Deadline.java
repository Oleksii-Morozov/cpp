package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public record Deadline(LocalDate date, LocalTime time) implements Serializable {
    @Override
    public String toString() {
        return String.format("%s %s", date, time);
    }
}
