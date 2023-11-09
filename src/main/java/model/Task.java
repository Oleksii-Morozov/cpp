package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

@Getter
@Setter
public class Task implements Serializable {
    Integer id;
    String title;
    String description;
    Deadline deadline;
    Boolean isPriority;
    Boolean isDone;
    List<Subtask> subtasks;

    Deadline startDate;
    Deadline endDate;

    public Task(Integer id, String title, String description, Deadline deadline, Boolean isPriority, Boolean isDone, List<Subtask> subtasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isPriority = isPriority;
        this.isDone = isDone;
        this.subtasks = subtasks;

        this.startDate = new Deadline(LocalDate.now(), LocalTime.now());
    }

    private String Priority() {
        if (isPriority) {
            return "Priority";
        } else {
            return "Not priority";
        }
    }

    private String Done() {
        if (isDone) {
            return "Done";
        } else {
            return "Not done";
        }
    }

    public void setIsDone() {
        isDone=true;
        endDate = new Deadline(LocalDate.now(), LocalTime.now());
    }

    private String wastedTime() {
        Period period = Period.between(startDate.date(), endDate.date());
        Duration duration = Duration.between(startDate.time(), endDate.time());

        long totalHours = period.toTotalMonths() * 24 * 30 + ((long) period.getDays()) * 24 + duration.toHours();

        return String.format("%d hours", totalHours);
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | %s | %s | %s", id, title, description, deadline, Priority(), Done(), wastedTime());
    }

}
