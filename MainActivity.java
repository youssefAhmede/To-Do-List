package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText taskInput;
    private Button addButton, viewCompletedButton;
    private ListView taskListView;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        addButton = findViewById(R.id.addButton);
        viewCompletedButton = findViewById(R.id.viewCompletedButton);
        taskListView = findViewById(R.id.taskListView);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        taskListView.setAdapter(taskAdapter);

        addButton.setOnClickListener(v -> {
            String taskText = taskInput.getText().toString().trim();
            if (!taskText.isEmpty()) {
                taskList.add(new Task(taskText, false));
                taskAdapter.notifyDataSetChanged();
                taskInput.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Enter a task!", Toast.LENGTH_SHORT).show();
            }
        });

        viewCompletedButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CompletedTasksActivity.class);
            intent.putParcelableArrayListExtra("completedTasks", taskList);
            startActivity(intent);
        });
    }
}
