package net.unibave.todolist_java.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class TaskRepository {

    TaskRestApi api;

    public TaskRepository(Context context) {
        api = RetrofitClient.getInstance().create(TaskRestApi.class);
    }

    public List<Task> findAll() {

        List<Task> tasks = new ArrayList<>();
        Call<List<Task>> callTasks = api.findAll();

        try {
            tasks.addAll(callTasks.execute().body());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public void create(Task task) {
        Call<Response> call = api.create(task);
        try {
            Response response = call.execute().body();
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getError());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void edit(Task task) {
        Call<Response> call = api.edit(task.getId(), task);
        try {
            Response response = call.execute().body();
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getError());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Task task) {
        Call<Response> call = api.delete(task.getId());
        try {
            Response response = call.execute().body();
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getError());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


