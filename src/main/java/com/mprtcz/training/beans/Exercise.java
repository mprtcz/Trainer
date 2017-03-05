package com.mprtcz.training.beans;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Azet on 2016-02-28.
 */
public class Exercise {

    private int reps;
    private String name;
    private java.time.LocalDateTime dateTime;

    public Exercise(String name, int reps) {
        this.reps = reps;
        this.name = name;
        this.dateTime = LocalDateTime.now();
    }

    public static class Builder{
        private final int reps;
        private final String name;

        private String dateTimeString = "";

        public Builder(String exerciseName, int reps){
            this.name = exerciseName;
            this.reps = reps;
        }

        public Builder dateTime(String newDateTime){
            dateTimeString = newDateTime;
            return this;
        }

        public Exercise build(){
            return new Exercise(this);
        }

    }

    private Exercise(Builder builder){
        reps = builder.reps;
        name = builder.name;
        dateTime = LocalDateTime.parse(builder.dateTimeString);
    }

    @Override
    public String toString(){
            return "Name: " + name +" reps: " +reps +" date: " +dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));

    }

    public int getReps() {
        return reps;
    }

    public String getName() {
        return name;
    }

    public String getDateTimeString() {
        return dateTime.toString().replace("T", " ");
    }

    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    public void addRepsToBean(int reps){
        this.reps += reps;
    }
}