package app.main;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;


@Entity(tableName = "tasks")
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    public String description;
    public Date deadline;
    public boolean finished;

    public Task(String description, Date deadline,boolean finished) {
        this.description = description;
        this.deadline = deadline;
        this.finished = finished;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
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
