package Tasks.Models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<TaskItems> tasks;

    public User(String username) {
        this.username = username;
        this.tasks = new ArrayList<>();
        };

    public String getUsername() {
        return username;
    }

    public List<TaskItems> getTasks() {
        return tasks;
    }

    public void addTaskItems(TaskItems taskItems) {
        tasks.add(taskItems);
    }
}

