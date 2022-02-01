package com.example.trainingwheels;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

public class ExerciseData {


    private String name;

    private String reps;

    private String sets;

    private String link;

    private String image;

    private String userset;

    public ExerciseData() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getUserset() {
        return userset;
    }

    public void setUserset(String userset) {
        this.userset = userset;
    }



}
