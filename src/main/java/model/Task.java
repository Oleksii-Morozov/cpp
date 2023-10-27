package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Task implements Serializable {
    Integer id;
    String title;
    String description;
    Deadline deadline;
    Boolean isPriority;
    Boolean isDone;
    List<Subtask> subtasks;

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

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | %s | %s", id, title, description, deadline, Priority(), Done());
    }

}
