package com.example.tasks.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasks.R;
import com.example.tasks.utils.TaskDatabase;

public class UpdateTaskActivity extends AppCompatActivity {

    ImageView backButton;
    EditText taskNameEt;
    TextView updateButton, deleteButton;

    String id, taskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        backButton = findViewById(R.id.backButton_update);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        taskNameEt = findViewById(R.id.updateTaskEditText);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        getAndSetIntentData();

        updateButton.setOnClickListener(view -> {
            taskName = taskNameEt.getText().toString().trim();
            TaskDatabase taskDb = new TaskDatabase(UpdateTaskActivity.this);
            taskDb.updateData(id, taskName);
            finish();
        });

        deleteButton.setOnClickListener(view -> {
            confirmDialog();
        });

    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete task");
        builder.setMessage("Are you sure you want to delete this?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TaskDatabase taskDb = new TaskDatabase(UpdateTaskActivity.this);
                taskDb.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            recreate();
        }
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("taskName")) {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            taskName = getIntent().getStringExtra("taskName");

            //Setting intent data
            taskNameEt.setText(taskName);
        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
    }
}