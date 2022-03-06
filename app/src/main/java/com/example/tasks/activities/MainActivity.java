package com.example.tasks.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tasks.R;
import com.example.tasks.adapter.TaskAdapter;
import com.example.tasks.utils.TaskDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton homeFab;
    RecyclerView recyclerView;
    TextView noData;

    private int backFlag = 0;

    TaskDatabase taskDb;
    ArrayList<String> task_id, task_name;
    public TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.homeRecyclerView);
        noData = findViewById(R.id.noDataTv);

        taskDb = new TaskDatabase(MainActivity.this);
        task_id = new ArrayList<>();
        task_name = new ArrayList<>();

        homeFab = findViewById(R.id.homeFabButton);
        homeFab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 1);
        });

        storeDataInArrays();

        adapter = new TaskAdapter(MainActivity.this, this, task_id, task_name);

        if(adapter.getItemCount()==0) {
            noData.setVisibility(View.VISIBLE);
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            recreate();
        }
    }

    private void storeDataInArrays() {
        Cursor cursor = taskDb.readAllTask();
        if(cursor.getCount()!=0) {
            while (cursor.moveToNext()) {
                task_id.add(cursor.getString(0));
                task_name.add(cursor.getString(1));
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(backFlag==0) {
            backFlag = 1;
        } else {
            super.onBackPressed();
        }
    }
}