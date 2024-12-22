package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(@NonNull Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        Task task = getItem(position);

        TextView taskName = convertView.findViewById(R.id.taskName);
        CheckBox taskCheckbox = convertView.findViewById(R.id.taskCheckbox);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        taskName.setText(task.getTaskName());
        taskCheckbox.setChecked(task.isCompleted());

        taskCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> task.setCompleted(isChecked));

        deleteButton.setOnClickListener(v -> {
            remove(task);
            notifyDataSetChanged();
            Toast.makeText(getContext(), "Task deleted!", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
