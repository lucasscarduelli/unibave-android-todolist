package net.unibave.todolist_java.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.unibave.todolist_java.R;
import net.unibave.todolist_java.controller.TaskController;
import net.unibave.todolist_java.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Task> taskList = new ArrayList<>();

    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView recycleViewTasks;
    TextView emptySpaceTasks;

    TaskController taskController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskController = new TaskController(this);
        taskList = taskController.findAll();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle(getString(R.string.title_tasks));

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new FloatingActionButtonClickListener());

        recycleViewTasks = findViewById(R.id.recycleViewTasks);
        recycleViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recycleViewTasks.setAdapter(new ItemTaskAdapter(taskList));

        emptySpaceTasks = findViewById(R.id.emptySpaceTasks);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        taskList = taskController.findAll();
        recycleViewTasks.setAdapter(new ItemTaskAdapter(taskList));
        recycleViewTasks.getAdapter().notifyDataSetChanged();

        emptySpaceTasks.setVisibility(taskList.isEmpty() ? View.VISIBLE : View.GONE);
    }

    protected void createTask(String name) {
        String message = "";
        try {
            taskController.create(name);
            message = getString(R.string.new_task_message);
        } catch (Exception e) {
            message = getString(R.string.new_task_message_error) + e.getMessage();
        } finally {
            Snackbar.make(fab, message, Snackbar.LENGTH_LONG).show();
            refresh();
        }
    }

    private void createTaskDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_task, null);
        EditText dialogTaskName = view.findViewById(R.id.taskName);
        builder
            .setView(view)
            .setPositiveButton(getString(R.string.button_add), (dialog, which) -> {
                String taskName = dialogTaskName.getText().toString();
                createTask(taskName);
            })
            .setNegativeButton(getString(R.string.button_cancel), (dialog, which) -> {
            });

        builder.create().show();
    }

    private class FloatingActionButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            createTaskDialog();
        }
    }
}


