package net.unibave.todolist_java.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import net.unibave.todolist_java.model.Task;
import net.unibave.todolist_java.model.TaskLocalDatabaseRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskController {

    TaskLocalDatabaseRepository repository;

    public TaskController(Context context) {
        repository = new TaskLocalDatabaseRepository(context);
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void create(String name) {
        String id = UUID.randomUUID().toString();

        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setCreatedAt(new Date());
        task.setDone(false);

        validateName(task);

        repository.create(task);
    }

    private void validateName(Task task) {
        if (task.getName().isEmpty() || task.getName().length() > 40) {
            throw new RuntimeException("Invalid task name!");
        }
    }

}

