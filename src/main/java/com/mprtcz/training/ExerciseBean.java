package com.mprtcz.training;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Azet on 2016-02-28.
 */
public class ExerciseBean {

    private int reps;
    private String exerName;
    private java.time.LocalDateTime dateTime;
    private String stringRepresentation;
    private String dateTimeString;

    ExerciseBean(String name, int reps) {
        this.reps = reps;
        this.exerName = name;
        this.dateTime = LocalDateTime.now();
        dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss"));
    }

    static class Builder{
        private final int reps;
        private final String exerName;

        private String dateTimeString = "";

        Builder(String exerciseName, int reps){
            this.exerName = exerciseName;
            this.reps = reps;
        }

        Builder dateTime(String newDateTime){
            dateTimeString = newDateTime;
            return this;
        }

        ExerciseBean build(){
            return new ExerciseBean(this);
        }

    }

    private ExerciseBean(Builder builder){
        reps = builder.reps;
        exerName = builder.exerName;
        stringRepresentation = "Name: " +exerName +" reps: " +reps +" on: " +builder.dateTimeString;
    }

    @Override
    public String toString(){
        if(stringRepresentation!=null) {
            return stringRepresentation;
        } else {
            return "Name: " +exerName +" reps: " +reps;
        }
    }

    String toDayString(){
        if(stringRepresentation!=null) {
            String[] parts = stringRepresentation.split("on: ");
            return parts[1].substring(0, 10);
        } else {
            return "?";
        }
    }

    int getReps() {
        return reps;
    }

    String getExerName() {
        return exerName;
    }

    LocalDateTime getDateTime() {
        return dateTime;
    }

    void addRepsToBean(int reps){
        this.reps += reps;
    }
}