package com.example.trainingwheels;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;


public class ExerciseDataAdapter extends FirebaseRecyclerAdapter<ExerciseData, ExerciseDataAdapter.ExerciseViewholder> {

    public ExerciseDataAdapter(@NonNull FirebaseRecyclerOptions<ExerciseData> options) {
        super(options);
    }

    @NotNull
    @Override
    public ExerciseViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ExerciseViewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExerciseViewholder holder, int position, @NonNull ExerciseData model) {
        holder.name.setText(model.getName());
        Picasso.get().load(model.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), model.getName(), Toast.LENGTH_SHORT).show();
            if (model.getName().equals("Running")) {
                Intent r = new Intent(v.getContext(), RunningActivity.class);
                r.putExtra("exercise",model.getName());
                v.getContext().startActivity(r);
            }else {
            Intent i = new Intent(v.getContext(), ExerciseActivity.class);
            i.putExtra("exercise", model.getName());
            i.putExtra("reps", model.getReps());
            i.putExtra("sets", model.getSets());
            i.putExtra("link", model.getLink());
            v.getContext().startActivity(i);
        }
        });

    }


    static class ExerciseViewholder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;

        public ExerciseViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.card_text);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
