package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class CompletedTasksActivity extends AppCompatActivity {
    private ListView completedListView;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);

        completedListView = findViewById(R.id.completedListView);

        ArrayList<Task> allTasks = getIntent().getParcelableArrayListExtra("completedTasks");
        ArrayList<Task> completedTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }

        taskAdapter = new TaskAdapter(this, completedTasks);
        completedListView.setAdapter(taskAdapter);
    }
}
