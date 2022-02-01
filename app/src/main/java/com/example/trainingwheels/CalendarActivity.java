package com.example.trainingwheels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Button addEvent = findViewById(R.id.button);
        EditText eventTitle = findViewById(R.id.eventTitle);
        EditText description = findViewById(R.id.description);

        addEvent.setOnClickListener(v -> {
            if (!eventTitle.getText().toString().isEmpty() && !description.getText().toString().isEmpty()) {

                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, eventTitle.getText().toString());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                startActivity(intent);

            } else {
                Toast.makeText(v.getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}