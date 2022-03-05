package com.example.tasks.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasks.R;
import com.example.tasks.activities.UpdateTaskActivity;
import com.example.tasks.utils.TaskDatabase;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final Context context;
    private final Activity activity;
    private final ArrayList task_id, task_name;

    Animation translate_anim;

    int position;

    public TaskAdapter(Activity activity, Context context, ArrayList task_id, ArrayList task_name) {
        this.activity = activity;
        this.context = context;
        this.task_id = task_id;
        this.task_name = task_name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.taskNameTv.setText(String.valueOf(task_name.get(position)));

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateTaskActivity.class);
            intent.putExtra("id", String.valueOf(task_id.get(position)));
            intent.putExtra("taskName", String.valueOf(task_name.get(position)));
            //to prevent app from crashing in emulator
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskNameTv;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameTv = itemView.findViewById(R.id.item_task_name);
            cardView = itemView.findViewById(R.id.item_cardView);

            //Animate recyclerView
//            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            cardView.setAnimation(translate_anim);
        }
    }

}
