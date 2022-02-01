package com.example.trainingwheels;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class IndividualActivity extends AppCompatActivity {

    ExerciseDataAdapter adapter;
    DatabaseReference mbase;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_individual);

        mbase = FirebaseDatabase.getInstance().getReference();

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navView = findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent i;

                if (id == R.id.nav_calendar) {
                    i = new Intent(IndividualActivity.this, CalendarActivity.class);
                    startActivity(i);
                } else if (id == R.id.nav_share) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    String shareBody = "https://github.com/Wigsicle/Training-Wheels";
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "My App");
                    intent.putExtra(Intent.EXTRA_TEXT, "Check out my app!" + " " + "Here:" + " " + shareBody);
                    startActivity(Intent.createChooser(intent, "Share"));
                } else if (id == R.id.nav_exit) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(IndividualActivity.this);
                    builder.setTitle(R.string.app_name);
                    builder.setIcon(R.drawable.tire);
                    builder.setMessage("Do you want to exit?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

                return true;
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ExerciseData> options = new FirebaseRecyclerOptions.Builder<ExerciseData>().setQuery(mbase, ExerciseData.class).build();

        adapter = new ExerciseDataAdapter(options);

        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(null); //Fixes the back button crashing everything when selecting stuff on recyclerView cardView.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {

        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {

        super.onStop();
        adapter.stopListening();
    }
}