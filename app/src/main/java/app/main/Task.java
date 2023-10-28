package app.main;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class Task implements Serializable {
    public String description;
    public Date deadline;
    public boolean finished;

    public Task(String description, Date deadline,boolean finished) {
        this.description = description;
        this.deadline = deadline;
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", deadline=" + deadline +
                ", finished=" + finished +
                '}';
    }
}
