package net.unibave.todolist_java.model;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class TaskMemoryRepository {

    List<Task> tasks = new ArrayList<>();

    public void create(Task task) {
        tasks.add(task);
    }

    public List<Task> findAll() {
        return tasks;
    }

    public Task findById(String id) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return tasks.stream()
                    .filter(task -> task.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }

        return null;
    }
}




