package com.example.tasks.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tasks.R;
import com.example.tasks.utils.TaskDatabase;

public class AddTaskActivity extends AppCompatActivity {

    ImageView backButton;
    EditText taskNameEt;
    TextView doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        backButton = findViewById(R.id.backBn_addTask);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        taskNameEt = findViewById(R.id.addNewTaskEditText);
        doneButton = findViewById(R.id.doneBn_addTask);

        doneButton.setOnClickListener(view -> {
            TaskDatabase taskDb = new TaskDatabase(AddTaskActivity.this);
            taskDb.addTask(taskNameEt.getText().toString().trim());
            taskNameEt.setText("");
            finish();
        });
    }

}