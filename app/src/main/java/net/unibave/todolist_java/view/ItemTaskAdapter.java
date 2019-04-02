package net.unibave.todolist_java.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import net.unibave.todolist_java.R;
import net.unibave.todolist_java.model.Task;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTaskAdapter extends RecyclerView.Adapter<ItemTaskAdapter.ViewHolder> {

    List<Task> tasks = new ArrayList<>();
    MainActivity activity;

    public ItemTaskAdapter(MainActivity activity, List<Task> tasks) {
        this.activity = activity;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskName.setText(task.getName());

        holder.menu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(activity, holder.menu);
            popupMenu.inflate(R.menu.task_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        activity.editTaskDialog(task);
                        break;
                    case R.id.menu_delete:
                        activity.deleteTaskDialog(task);
                        break;
                    case R.id.menu_done:
                        break;
                    case R.id.menu_reset:
                        break;
                }
                return true;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        ImageView menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            menu = itemView.findViewById(R.id.menu);
        }
    }
}

